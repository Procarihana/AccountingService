package com.procarihana.AccountingService.utils;

import com.procarihana.accounting.Dao.Mapper.UserInfoMapper;
import com.procarihana.accounting.moudle.presistence.UserInfo;

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
