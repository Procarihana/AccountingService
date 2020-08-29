package com.procarihana.accounting.Dao;

import com.procarihana.accounting.Dao.Mapper.TagMapper;
import com.procarihana.accounting.moudle.presistence.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagDaoImpl implements TagDao {
    private final TagMapper tagMapper;

    @Override
    public void createTag(Tag tag) {
        tagMapper.insert(tag);

    }

    @Override
    public Tag getTagByTadDescription(String description, Long userId) {
        return tagMapper.getTagByTagDescription(description, userId);
    }

    @Override
    public Tag getTagByTagId(Long tagId) {
        val tag = tagMapper.getTagByTagId(tagId);
        log.debug("The tag item from db:{}", tag);
        return tag;
    }

    @Override
    public void updateTag(Tag updateTag) {
        tagMapper.updateTag(updateTag);

    }

    @Override
    public List<Tag> getTagListByTagIds(List<Long> tagIds) {
        return tagMapper.getTagListByTagIds(tagIds);
    }
}
