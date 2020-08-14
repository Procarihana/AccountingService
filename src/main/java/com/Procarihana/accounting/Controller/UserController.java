package com.procarihana.accounting.controller;

import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.converter.commonToService.UserInfoCToSeConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.moudle.common.UserInfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1.0/users/")
@Slf4j
@RestController
public class UserController {
    private final UserInfoManager userInfoManager;
    private final UserInfoCToSeConverter userInfoCToSeConverter;

    /**
     * Get user information by user id.
     *
     * @param userInfoManager        ;  <b>must not</b> be null
     * @param userInfoCToSeConverter ; <b>must not</b> be null
     */
    @Autowired
    public UserController(final UserInfoManager userInfoManager,
                          final UserInfoCToSeConverter userInfoCToSeConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoCToSeConverter = userInfoCToSeConverter;
    }

    /**
     * Get user information by specific user id.
     *
     * @param userId the user id
     * @return user information response entity.
     */

    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<com.procarihana.accounting.moudle.service.UserInfo> getUserInfoByUserId(
        @PathVariable("id") Long userId) {
        log.info("Get user info by user id {}", userId);
        if (userId == null | userId <= 0) {
            throw new InvalidParameterException(String.format("The user id %s is invalid.", userId));
        }
        UserInfo userInfo = userInfoManager.getUserInfoByUserID(userId);
        com.procarihana.accounting.moudle.service.UserInfo userInfoToReturn = userInfoCToSeConverter.convert(userInfo);
        assert userInfoToReturn != null;
        return ResponseEntity.ok(userInfoToReturn); //为空的时候会抛出异常，所以不会为空
    }

}
