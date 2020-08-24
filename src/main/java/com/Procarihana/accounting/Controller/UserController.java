package com.procarihana.accounting.controller;

import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.converter.commonToService.UserInfoCToSeConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.moudle.common.UserInfo;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("v1.0/users")
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

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<com.procarihana.accounting.moudle.service.UserInfo> getUserInfoByUserId(
        @PathVariable("id") @NotNull Long userId) {
        log.debug("Get user info by user id {}", userId);
        if (userId <= 0L) {
            throw new InvalidParameterException(String.format("The user id %s is invalid.", userId));
        }
        UserInfo userInfo = userInfoManager.getUserInfoByUserID(userId);
        com.procarihana.accounting.moudle.service.UserInfo userInfoToReturn = userInfoCToSeConverter.convert(userInfo);
        assert userInfoToReturn != null;
        return ResponseEntity.ok(userInfoToReturn);
    }

    /**
     * Register user.
     *
     * @param userInfo
     * <b>must not</b> be null
     * @return
     */

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<com.procarihana.accounting.moudle.service.UserInfo> register(@RequestBody UserInfo userInfo) {
        val userInfoResultC = userInfoManager.register(userInfo.getUsername(), userInfo.getPassword());
        val result = userInfoCToSeConverter.convert(userInfoResultC);
        assert result != null;
        return ResponseEntity.ok(result);

        //    @PostMapping(produces = "application/json", consumes = "application/json ")
        //    public ResponseEntity<com.procarihana.accounting.moudle.service.UserInfo> register(
        //        @RequestParam("username") String username,
        //        @RequestParam("password") String password) {
        //
        //        val userInfo = userInfoManager.register(username, password);
        //        return ResponseEntity.ok(userInfoCToSeConverter.convert(userInfo));


    }

}
