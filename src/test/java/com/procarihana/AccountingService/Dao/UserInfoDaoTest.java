package com.procarihana.AccountingService.Dao;

import com.procarihana.accounting.Dao.Mapper.UserInfoMapper;
import com.procarihana.accounting.Dao.UserInfoDaoImpl;
import com.procarihana.accounting.moudle.presistence.UserInfo;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInfoDaoTest {
    @Mock
    private UserInfoMapper userInfoMapper;
    @InjectMocks
    private UserInfoDaoImpl userInfoDao;

    @Test
    public void getUserByUserIdTest() {
        Long userId = 100L;
        String username = "222";
        String password = "222";
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();

        UserInfo userInfo = UserInfo.builder()
            .id(userId)
            .createTime(createTime)
            .updateTime(updateTime)
            .password(password)
            .username(username)
            .build();
        doReturn(userInfo).when(userInfoMapper).getUserInfoByyUserId(userId);

        val result = userInfoDao.getUserInfoById(userId);
        Assertions.assertEquals(userInfo, result);
        verify(userInfoMapper).getUserInfoByyUserId(userId);


    }

}