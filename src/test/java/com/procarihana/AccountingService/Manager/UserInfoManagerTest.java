package com.procarihana.AccountingService.Manager;


import com.procarihana.accounting.Dao.UserInfoDao;
import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.Manager.UserInfoManagerImpl;
import com.procarihana.accounting.controller.UserController;
import com.procarihana.accounting.converter.commonToService.UserInfoCToSeConverter;
import com.procarihana.accounting.moudle.presistence.UserInfo;
import com.procarihana.accounting.converter.presisitenceToCommon.UserInfoPresToComConverter;
import com.procarihana.accounting.exception.ResourceNotFoundException;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class UserInfoManagerTest {
    private UserInfoManager userInfoManager;
    Long userId = 1L;
    String username = "111";
    String password = "111";
    UserInfo userInfo = UserInfo.builder()
        .id(userId)
        .password(password)
        .username(username)
        .build();

    @Mock
    private UserController userController;

    @Mock
    private UserInfoDao userInfoDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);  // 用来提醒Mockito来识别对象
        userInfoManager = new UserInfoManagerImpl(userInfoDao, new UserInfoPresToComConverter());
    }

    @Test
    public void testUserInfoByUserId() {
        doReturn(userInfo).when(userInfoDao).getUserInfoById(userId);

        assertThat(userInfo).isNotNull()
            .hasFieldOrPropertyWithValue("username", username)
            .hasFieldOrPropertyWithValue("id", userId)
            .hasFieldOrPropertyWithValue("password", password);
        val result = userInfoManager.getUserInfoByUserID(userId);
        assertThat(result).isNotNull()
            .hasFieldOrPropertyWithValue("username", username)
            .hasFieldOrPropertyWithValue("id", userId)
            .hasFieldOrPropertyWithValue("password", password);
        verify(userInfoDao).getUserInfoById(userId);
    }

    @Test
    public void testUserInfoByInvalidUserId() {
        Long userInvalidId = -1L;
        doReturn(null).when(userInfoDao).getUserInfoById(userInvalidId);
        Assertions
            .assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByUserID(userInvalidId));
        verify(userInfoDao).getUserInfoById(userInvalidId);
    }

    @Test
    public void testGetUserInfoByUsername() {
        doReturn(userInfo).when(userInfoDao).getUserInfoByUsername(username);
        val result = userInfoManager.getUserInfoByUsername(username);
        assertThat(result).isNotNull()
            .hasFieldOrPropertyWithValue("username", username)
            .hasFieldOrPropertyWithValue("id", userId)
            .hasFieldOrPropertyWithValue("password", password);
        verify(userInfoDao).getUserInfoByUsername(username);
    }

    @Test
    void testUserInfoByInvalidUsername() {
        String invalidUsername = "222";
        doReturn(null).when(userInfoDao).getUserInfoByUsername(invalidUsername);
        Assertions.assertThrows(ResourceNotFoundException.class,
            () -> userInfoManager.getUserInfoByUsername(invalidUsername));
        verify(userInfoDao).getUserInfoByUsername(invalidUsername);

    }

//    @Test
//    public void testregister(){
//        Long userid = 2L;
//        String username = "111";
//        String password = "111";
//        LocalDateTime createTime = LocalDateTime.now();
//        LocalDateTime updateTime = LocalDateTime.now();
//        val userInfo = com.procarihana.accounting.moudle.common.UserInfo.builder()
//            .id(userid)
//            .password(password)
//            .username(username)
//            .updateTime(createTime)
//            .createTime(updateTime)
//            .build();
//        doReturn(ResponseEntity.ok(new UserInfoCToSeConverter().convert(userInfo))).when(userController).register(username,password);
//       assertThat(userInfo).isNotNull()
//           .hasFieldOrPropertyWithValue("username", username)
//           .hasFieldOrPropertyWithValue("id", userid)
//           .hasFieldOrPropertyWithValue("password", password)
//           .hasFieldOrPropertyWithValue("createTime", createTime);
//       verify(userController).register(username,password);
//
//
//    }


}
