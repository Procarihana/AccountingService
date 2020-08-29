package com.procarihana.accounting.converter.commonToService;

import com.procarihana.accounting.moudle.common.Record;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;


@Component
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class RecordC2SConverter extends Converter<Record, com.procarihana.accounting.moudle.service.Record> {

    private final TagC2SConverter tagC2SConverter;

    @VisibleForTesting
    public RecordC2SConverter(TagC2SConverter tagC2SConverter) {
        this.tagC2SConverter = tagC2SConverter;
    }

    @Override
    protected com.procarihana.accounting.moudle.service.Record doForward(Record record) {
        val tagList = ImmutableList.copyOf(tagC2SConverter.convertAll(record.getTagList()));
        return com.procarihana.accounting.moudle.service.Record.builder()
            .id(record.getId())
            .note(record.getNote())
            .amount(record.getAmount())
            .category(record.getCategory())
            .tagList(tagList)
            .userId(record.getUserId())
            .build();
    }

    @Override
    protected Record doBackward(com.procarihana.accounting.moudle.service.Record record) {
        val result = Record.builder()
            .amount(record.getAmount())
            .category(record.getCategory())
            .id(record.getId())
            .note(record.getNote())
            .userId(record.getUserId())
            .build();
        if (record.getTagList() != null) {
            val tagList = ImmutableList.copyOf(tagC2SConverter.reverse().convertAll(record.getTagList()));
            result.setTagList(tagList);
        }
        return result;
    }
}
