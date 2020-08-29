package com.procarihana.accounting.controller;

import com.procarihana.accounting.Manager.TagManager;
import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.converter.commonToService.TagC2SConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.moudle.service.Tag;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1.0/tags")
public class TagController {
    private final TagManager tagManager;
    private final TagC2SConverter tagC2SConverter;
    private final UserInfoManager userInfoManager;

    /**
     * Controller for TagController.
     * @param tagManager  tagmanager
     * @param tagC2SConverter moudle conment tag converter
     * @param userInfoManager user info manager
     */
    @Autowired
    public TagController(TagManager tagManager,
                         TagC2SConverter tagC2SConverter,
                         UserInfoManager userInfoManager) {
        this.tagManager = tagManager;
        this.tagC2SConverter = tagC2SConverter;
        this.userInfoManager = userInfoManager;
    }

    /**
     * Create tag with related information.
     *
     * @param tag tag information to create.
     * @return the create tag.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Tag createTag(@RequestBody Tag tag) {
        if (tag.getDescription() == null || tag.getDescription().isEmpty() || tag.getUserId() == null) {
            throw new InvalidParameterException("The description and user id must be not null or empty.");
        }

        com.procarihana.accounting.moudle.common.Tag resource =
            tagManager.createTag(tag.getDescription(), tag.getUserId());
        return tagC2SConverter.convert(resource);
    }


    /**
     * Get tag by tag id.
     *
     * @param tagId the specific tag id.
     * @return The relate tag information.
     */
    @GetMapping(path = "/{id}", produces = "application/json;charset=utf-8")
    public Tag getTagByTagId(@PathVariable("id") Long tagId) {
        log.debug("Get tag info by tag id {}", tagId);
        if (tagId == null || tagId <= 0L) {
            throw new InvalidParameterException(
                (String.format("The tagId [%s] must be not empty and positive.", tagId)));
        }
        val tag = tagManager.getTagByTagId(tagId);
        return tagC2SConverter.convert(tag);
    }

    /**
     * Update tag information for specific tag.
     *
     * @param tagId the specific tag id.
     * @param tag   the tag information.
     * @return the update tag information.
     */

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Tag updateTag(@PathVariable("id") Long tagId, @RequestBody Tag tag) {
        String status = tag.getStatus();
        if (tag.getUserId() == null || tag.getUserId() <= 0L) {
            throw new InvalidParameterException("The user id is empty or invalid");
        }
        if (tagId == null || tagId <= 0L) {
            throw new InvalidParameterException("The tag id is empty or invalid");
        }
        if (status != null && "ENABLE".equals(status) && "DISABLE".equals(status)) {
            throw new InvalidParameterException(String.format("The status [%s] to update is invalid status", status));
        }
        checkUserIdIsExisting(tag);
        tag.setId(tagId);

        val tagInC = tagC2SConverter.reverse().convert(tag);
        val newTag = tagManager.updateTag(tagInC);
        return tagC2SConverter.convert(newTag);
    }

    private void checkUserIdIsExisting(Tag tag) {
        userInfoManager.getUserInfoByUserID(tag.getUserId());
    }
}
