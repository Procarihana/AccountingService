package com.procarihana.accounting.converter.commonToService;

import com.procarihana.accounting.moudle.common.Tag;

import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagC2SConverter extends Converter<Tag, com.procarihana.accounting.moudle.service.Tag> {
    @Override
    protected com.procarihana.accounting.moudle.service.Tag doForward(Tag tag) {
        return com.procarihana.accounting.moudle.service.Tag
            .builder()
            .description(tag.getDescription())
            .id(tag.getId())
            .userId(tag.getUserId())
            .status(tag.getStatus())
            .build();
    }

    @Override
    protected Tag doBackward(com.procarihana.accounting.moudle.service.Tag tag) {
        return Tag.builder()
            .description(tag.getDescription())
            .id(tag.getId())
            .userId(tag.getUserId())
            .status(tag.getStatus())
            .build();
    }
}
