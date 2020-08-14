package com.procarihana.accounting.converter.commonToService;

import com.procarihana.accounting.moudle.service.UserInfo;

import com.google.common.base.Converter;

import javax.validation.constraints.NotNull;


public class UserInfoCToSeConverter extends Converter<com.procarihana.accounting.moudle.common.UserInfo, UserInfo> {
    @Override
    protected @NotNull UserInfo doForward(@NotNull com.procarihana.accounting.moudle.common.UserInfo userInfo) {
        return com.procarihana.accounting.moudle.service.UserInfo.builder()
            .id(userInfo.getId())
            //不能向Service传递密码.password(userInfo.getUsername())
            .username(userInfo.getUsername())
            .build();
    }

    @Override
    protected com.procarihana.accounting.moudle.common.UserInfo doBackward(@NotNull UserInfo userInfo) {
        return com.procarihana.accounting.moudle.common.UserInfo.builder()
            .id(userInfo.getId())
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .build();
    }
}
