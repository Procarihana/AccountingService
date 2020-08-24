package com.procarihana.AccountingService.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.procarihana.AccountingService.converter.UserInfoCToSeConverterTest;
import com.procarihana.accounting.controller.UserController;
import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.moudle.common.UserInfo;
import com.procarihana.accounting.converter.commonToService.UserInfoCToSeConverter;
import com.procarihana.accounting.exception.GlobalExceptionHandler;
import com.procarihana.accounting.exception.InvalidParameterException;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
            new UserController(
                userInfoManager, new UserInfoCToSeConverter()))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @AfterEach
    void teatDown() {
        reset(userInfoManager);
    }

    @Test
    void getUserInfoByUserIdTest() throws Exception {
        String username = "666";
        String password = "666";
        Long userId = 2L;
        UserInfo userInfoC = UserInfo.builder()
            .id(userId)
            .username(username)
            .password(password)
            .build();
        doReturn(userInfoC).when(userInfoManager).getUserInfoByUserID(anyLong());

        mockMvc.perform(get("/v1.0/users/" + userId).contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=utf-8 "))
            .andExpect(content()
                .string(new ObjectMapper().writeValueAsString(new UserInfoCToSeConverter().convert(userInfoC))));
        //.andExpect(content().string("{\"id\":2,\"username\":\"666\",\"password\":null}"));
        verify(userInfoManager).getUserInfoByUserID(anyLong());
    }

    @Test
    void getUserInfoByInvalidUserId() throws Exception {
        Long userId = -2L;
        doThrow(new InvalidParameterException(String.format("The user id %s is invalid.", userId)))
            .when(userInfoManager)
            .getUserInfoByUserID(anyLong());

        mockMvc.perform(get("/v1.0/users/" + userId))
            .andExpect(status().is4xxClientError())
            .andExpect(content().contentType("application/json"))
            .andExpect(content()
                .string(
                    "{\"code\":\"USER_NOT_FOUND\",\"errorType\":\"Cline\",\"massage\":\"The user id -2 is invalid.\",\"statusCode\":400}"));
        verify(userInfoManager, never()).getUserInfoByUserID(anyLong());
    }
}
