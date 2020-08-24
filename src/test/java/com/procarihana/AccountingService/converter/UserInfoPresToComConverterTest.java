package com.procarihana.AccountingService.converter;

import com.procarihana.accounting.moudle.presistence.UserInfo;
import com.procarihana.accounting.converter.presisitenceToCommon.UserInfoPresToComConverter;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoPresToComConverterTest {
    private UserInfoPresToComConverter userInfoPresToComConverter = new UserInfoPresToComConverter();
    String username = "444";
    String password = "444";
    Long userId = 2L;
    LocalDateTime createTime = LocalDateTime.now();
    LocalDateTime updateTime = LocalDateTime.now();

    @Test
    void DoForwardTest() {
        UserInfo userInfoP = UserInfo.builder()
            .id(userId)
            .username(username)
            .password(password)
            .updateTime(updateTime)
            .createTime(createTime)
            .build();
        val result = userInfoPresToComConverter.convert(userInfoP);

        assertThat(result).isNotNull()
            .hasFieldOrPropertyWithValue("id", userId)
            .hasFieldOrPropertyWithValue("username", username)
            .hasFieldOrPropertyWithValue("password", password);
    }

    @Test
    void doBackward() {
        val userInfoC = com.procarihana.accounting.moudle.common.UserInfo.builder()
            .id(userId)
            .username(username)
            .password(password)
            .updateTime(updateTime)
            .createTime(createTime)
            .build();
        val result = userInfoPresToComConverter.reverse().convert(userInfoC);

        assertThat(result).isNotNull()
            .hasFieldOrPropertyWithValue("id", userId)
            .hasFieldOrPropertyWithValue("password", password)
            .hasFieldOrPropertyWithValue("username", username);
    }
}
