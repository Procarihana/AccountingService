package com.Procarihana.AccountingService.converter;

import com.Procarihana.AccountingService.Moudle.presistence.UserInfo;
import com.Procarihana.AccountingService.converter.presisitenceToCommon.UserInfoPresToComConverter;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoPresToComConverterTest {
    private UserInfoPresToComConverter userInfoPresToComConverter =new UserInfoPresToComConverter();
    String username = "444";
    String password = "444";
    Long userId = 2L;
    LocalDate createTime = LocalDate.now();
    LocalDate updateTime = LocalDate.now();

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
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("createTime", createTime)
                .hasFieldOrPropertyWithValue("updateTime", updateTime);
    }

    @Test
    void doBackward() {
        val userInfoC = com.Procarihana.AccountingService.Moudle.common.UserInfo.builder()
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
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("createTime", createTime)
                .hasFieldOrPropertyWithValue("updateTime", updateTime);
    }
}
