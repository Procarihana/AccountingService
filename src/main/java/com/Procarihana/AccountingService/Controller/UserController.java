package com.Procarihana.AccountingService.Controller;

import com.Procarihana.AccountingService.Manager.UserInfoManager;

import com.Procarihana.AccountingService.Moudle.service.UserInfo;
import com.Procarihana.AccountingService.converter.commonToService.UserInfoCToSeConverter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/users/")
@Slf4j
@RestController
public class UserController {
    private final UserInfoManager userInfoManager;
    private final UserInfoCToSeConverter userInfoCToSeConverter;
    @Autowired
    public UserController(final UserInfoManager userInfoManager,
                          final UserInfoCToSeConverter userInfoCToSeConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoCToSeConverter = userInfoCToSeConverter;
    }


    @GetMapping("/{id}")
    public UserInfo getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.info("Get user info by user id {}",userId);
        com.Procarihana.AccountingService.Moudle.common.UserInfo userInfo = userInfoManager.getUserInfoByUserID(userId);
        return userInfoCToSeConverter.convert(userInfo);
    }

}
