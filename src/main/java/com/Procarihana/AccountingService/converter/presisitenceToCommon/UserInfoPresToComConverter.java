package com.Procarihana.AccountingService.converter.presisitenceToCommon;

import com.Procarihana.AccountingService.Moudle.presistence.UserInfo;
import com.google.common.base.Converter;

import java.time.LocalDate;

public class UserInfoPresToComConverter extends Converter<UserInfo, com.Procarihana.AccountingService.Moudle.common.UserInfo> {
    @Override
    protected com.Procarihana.AccountingService.Moudle.common.UserInfo doForward(UserInfo userInfo) {
        return com.Procarihana.AccountingService.Moudle.common.UserInfo.builder()
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .id(userInfo.getId())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.Procarihana.AccountingService.Moudle.common.UserInfo userInfo) {
        return UserInfo.builder()
                .createTime(LocalDate.now())
                .updateTime(LocalDate.now())
                .id(userInfo.getId())
                .build();
    }
}
