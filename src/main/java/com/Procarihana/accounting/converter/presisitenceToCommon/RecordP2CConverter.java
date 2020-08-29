package com.procarihana.accounting.converter.presisitenceToCommon;

import com.procarihana.accounting.moudle.presistence.Record;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class RecordP2CConverter extends Converter<Record, com.procarihana.accounting.moudle.common.Record> {
    private static final String incomeCategory = "INCOME";
    private static final String outcomeCategory = "OUTCOME";
    private static final String disableStatus = "DISABLE";
    private static final String enableStatus = "ENABLE";

    private final TagP2CConverter tagP2CConverter;

    @VisibleForTesting
    public RecordP2CConverter(TagP2CConverter tagP2CConverter) {
        this.tagP2CConverter = tagP2CConverter;
    }

    @Override
    protected com.procarihana.accounting.moudle.common.Record doForward(Record record) {
        val tagList = ImmutableList.copyOf(tagP2CConverter.convertAll(record.getTagList()));
        return com.procarihana.accounting.moudle.common.Record
            .builder()
            .userId(record.getUserId())
            .note(record.getNote())
            .id(record.getId())
            .category(record.getCategory() != 1 ? outcomeCategory : incomeCategory)
            .amount(record.getAmount())
            .status(record.getStatus() != 1 ? disableStatus : enableStatus)
            .tagList(tagList)
            .build();

    }

    @Override
    protected Record doBackward(com.procarihana.accounting.moudle.common.Record record) {
        val result = Record.builder()
            .amount(record.getAmount())
            .category(incomeCategory.equals(record.getCategory()) ? 1 : 0)
            .id(record.getId())
            .note(record.getNote())
            .status(enableStatus.equals(record.getStatus()) ? 1 : 0)
            .userId(record.getUserId())
            .build();
        if (record.getTagList() != null) {
            val tagList = ImmutableList.copyOf(tagP2CConverter.reverse().convertAll(record.getTagList()));
            result.setTagList(tagList);
        }
        return result;
    }
}
