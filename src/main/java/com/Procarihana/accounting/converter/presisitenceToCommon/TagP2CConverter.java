package com.procarihana.accounting.converter.presisitenceToCommon;

import com.procarihana.accounting.moudle.presistence.Tag;

import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagP2CConverter extends Converter<Tag, com.procarihana.accounting.moudle.common.Tag> {
    private static final String ENABLE = "ENABLE";
    private static final String DISABLE = "DISABLE";

    @Override
    protected com.procarihana.accounting.moudle.common.Tag doForward(Tag tag) {
        return com.procarihana.accounting.moudle.common.Tag
            .builder()
            .description(tag.getDescription())
            .id(tag.getId())
            .userId(tag.getUserId())
            .status(tag.getStatus() == 1 ? ENABLE : DISABLE)
            .build();
    }

    @Override
    protected Tag doBackward(com.procarihana.accounting.moudle.common.Tag tag) {
        Tag tagInDb = Tag.builder()
            .description(tag.getDescription())
            .id(tag.getId())
            .userId(tag.getUserId())
            .build();
        if (tag.getStatus() != null) {
            tagInDb.setStatus(ENABLE.equals(tag.getStatus()) ? 1 : 0);
        }
        return tagInDb;
    }
}
