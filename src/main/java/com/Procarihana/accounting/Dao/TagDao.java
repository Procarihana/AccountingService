package com.procarihana.accounting.Dao;

import com.procarihana.accounting.moudle.presistence.Tag;

import java.util.List;


public interface TagDao {
    void createTag(Tag tag);

    Tag getTagByTadDescription(String description, Long userId);

    Tag getTagByTagId(Long userId);

    void updateTag(Tag updateTag);

    List<Tag> getTagListByTagIds(List<Long> tagIds);
}
