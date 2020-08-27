package com.procarihana.accounting.Manager;

import com.procarihana.accounting.Dao.TagDao;
import com.procarihana.accounting.converter.presisitenceToCommon.TagP2CConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.exception.ResourceNotFoundException;
import com.procarihana.accounting.moudle.common.Tag;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TagManagerImpl implements TagManager {
    private final Integer enableStatus = 1;
    private final TagDao tagDao;
    private final TagP2CConverter tagP2CConverter;

    @Autowired
    public TagManagerImpl(TagDao tagDao,
                          TagP2CConverter tagP2CConverter) {
        this.tagDao = tagDao;
        this.tagP2CConverter = tagP2CConverter;
    }

    @Override
    public Tag createTag(String description, Long userId) {
        Optional.ofNullable(tagDao.getTagByTadDescription(description, userId))
            .ifPresent(tag -> {
                throw new InvalidParameterException(
                    String.format("The related tag with description [%s] has been created", description));
            });
        val newTag = com.procarihana.accounting.moudle.presistence.Tag.builder()
            .description(description)
            .userId(userId)
            .status(enableStatus)
            .build();
        tagDao.createTag(newTag);
        return tagP2CConverter.convert(newTag);
    }

    @Override
    public Tag getTagByTagId(Long tagId) {
        return Optional.ofNullable(tagDao.getTagByTagId(tagId))
            .map(tagP2CConverter::convert)
            .orElseThrow(() -> new InvalidParameterException(
                String.format("The related tag with tag id [%s] was not found", tagId)));
    }

    @Override
    public Tag updateTag(Tag tag) {
        com.procarihana.accounting.moudle.presistence.Tag updateTag = tagP2CConverter.reverse().convert(tag);
        val tagIntoDb = Optional.ofNullable(tagDao.getTagByTagId(tag.getUserId()))
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("The related tag id [%s]was not found", tag.getId())));
        if (!tag.getUserId().equals(tagIntoDb.getUserId())) {
            throw new InvalidParameterException(
                String.format("The record id [%s] doesn't belong to user id:[%s]", tag.getId(), tag.getUserId()));
        }
        tagDao.updateTag(updateTag);
        return getTagByTagId(tag.getId());
    }
}
