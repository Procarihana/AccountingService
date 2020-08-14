package com.Procarihana.AccountingService.Controller;

import com.Procarihana.AccountingService.Manager.UserInfoManager;

import com.Procarihana.AccountingService.Moudle.common.UserInfo;
import com.Procarihana.AccountingService.converter.commonToService.UserInfoCToSeConverter;

import com.Procarihana.AccountingService.exception.ErrorResponse;
import com.Procarihana.AccountingService.exception.InvalidParameterException;
import com.Procarihana.AccountingService.exception.ResourceNotFoundException;
import com.Procarihana.AccountingService.exception.ServiceException;
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


    @Autowired
    public UserController(final UserInfoManager userInfoManager,
                          final UserInfoCToSeConverter userInfoCToSeConverter
    ) {
        this.userInfoManager = userInfoManager;
        this.userInfoCToSeConverter = userInfoCToSeConverter;

    }


    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.info("Get user info by user id {}", userId);
        if (userId == null |userId <=0){
         throw new InvalidParameterException(String.format("The user id %s is invalid.",userId));
        }
        UserInfo userInfo = userInfoManager.getUserInfoByUserID(userId);
        return ResponseEntity.ok(userInfoCToSeConverter.convert(userInfo)); //为空的时候会抛出异常，所以不会为空
    }
}
