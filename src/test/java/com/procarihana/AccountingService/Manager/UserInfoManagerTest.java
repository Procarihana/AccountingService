package com.procarihana.AccountingService.Manager;


import com.procarihana.accounting.Dao.UserInfoDao;
import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.Manager.UserInfoManagerImpl;
import com.procarihana.accounting.moudle.presistence.UserInfo;
import com.procarihana.accounting.converter.presisitenceToCommon.UserInfoPresToComConverter;
import com.procarihana.accounting.exception.ResourceNotFoundException;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class UserInfoManagerTest {
    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoDao userInfoDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);  // 用来提醒Mockito来识别对象
        userInfoManager = new UserInfoManagerImpl(userInfoDao, new UserInfoPresToComConverter());
    }

    @Test
    public void testUserInfoByUserId() {
        Long userId = 1L;
        String username = "111";
        String password = "111";
        LocalDate createTime = LocalDate.now();
        LocalDate updateTime = LocalDate.now();

        UserInfo userInfo = UserInfo.builder()
                .id(userId)
                .password(password)
                .username(username)
                .updateTime(createTime)
                .createTime(updateTime)
                .build();

        doReturn(userInfo).when(userInfoDao).getUserInfoById(userId);

        assertThat(userInfo).isNotNull()
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("createTime", createTime);
        val result = userInfoManager.getUserInfoByUserID(userId);
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("createTime", createTime);
        verify(userInfoDao).getUserInfoById(userId);
    }

    @Test
    public void testUserInfoByInvalidUserId() {
        Long userId = -1L;
        doReturn(null).when(userInfoDao).getUserInfoById(userId);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByUserID(userId));
        verify(userInfoDao).getUserInfoById(userId);
    }


}
