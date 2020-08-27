package com.procarihana.accounting.Manager;

import com.procarihana.accounting.moudle.common.Tag;

public interface TagManager {
    Tag createTag(String description, Long userId);

    Tag updateTag(Tag tag);

    Tag getTagByTagId(Long tagId);
}
