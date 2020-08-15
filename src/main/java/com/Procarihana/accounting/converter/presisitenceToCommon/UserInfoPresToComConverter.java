package com.procarihana.accounting.converter.presisitenceToCommon;

import com.procarihana.accounting.moudle.presistence.UserInfo;

import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoPresToComConverter extends
    Converter<UserInfo, com.procarihana.accounting.moudle.common.UserInfo> {
    @Override
    protected com.procarihana.accounting.moudle.common.UserInfo doForward(
        UserInfo userInfo) {
        return com.procarihana.accounting.moudle.common.UserInfo.builder()
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .createTime(userInfo.getCreateTime())
            .updateTime(userInfo.getUpdateTime())
            .id(userInfo.getId())
            .build();
    }

    @Override
    protected UserInfo doBackward(com.procarihana.accounting.moudle.common.UserInfo userInfo) {
        return UserInfo.builder()
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .createTime(LocalDate.now())
            .updateTime(LocalDate.now())
            .id(userInfo.getId())
            .build();
    }
}