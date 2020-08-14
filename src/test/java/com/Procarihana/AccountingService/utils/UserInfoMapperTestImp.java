package com.Procarihana.AccountingService.utils;

import com.Procarihana.AccountingService.Dao.Mapper.UserInfoMapper;
import com.Procarihana.AccountingService.Moudle.presistence.UserInfo;

import java.time.LocalDate;


public class UserInfoMapperTestImp implements UserInfoMapper {

    @Override
    public UserInfo getUserInfoByyUserId(Long id) {
        return id >0?UserInfo.builder()
                .id(id)
                .username("TestUsername")
                .password("TestPassword")
                .updateTime(LocalDate.now())
                .createTime(LocalDate.now())
                .build():null;
    }
}
