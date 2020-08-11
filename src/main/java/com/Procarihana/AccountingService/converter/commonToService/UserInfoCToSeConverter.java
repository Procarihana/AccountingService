package com.Procarihana.AccountingService.converter.commonToService;

import com.Procarihana.AccountingService.Moudle.common.UserInfo;
import com.google.common.base.Converter;


public class UserInfoCToSeConverter extends Converter<UserInfo, com.Procarihana.AccountingService.Moudle.service.UserInfo> {
    @Override
    protected com.Procarihana.AccountingService.Moudle.service.UserInfo doForward(UserInfo userInfo) {
        return com.Procarihana.AccountingService.Moudle.service.UserInfo.builder()
                .id(userInfo.getId())
                //不能向Service传递密码.password(userInfo.getUsername())
                .username(userInfo.getUsername())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.Procarihana.AccountingService.Moudle.service.UserInfo userInfo) {
        return UserInfo.builder().id(userInfo.getId())
                .password(userInfo.getPassword())
                .username(userInfo.getUsername())
                .build();
    }
}
