package com.Procarihana.AccountingService.converter;

import com.Procarihana.AccountingService.Moudle.common.UserInfo;
import com.Procarihana.AccountingService.converter.commonToService.UserInfoCToSeConverter;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserInfoCToSeConverterTest {
    private UserInfoCToSeConverter userInfoCToSeConverter = new UserInfoCToSeConverter();

    @Test
    void DoForwardTest() {
        String username = "444";
        String password = "444";
        Long userId = 2L;

        UserInfo userInfoC = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();
        val result = userInfoCToSeConverter.convert(userInfoC);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", null);
    }

    @Test
    void doBackward() {
        String username = "555";
        String password = "555";
        Long userId = 3L;

        val userInfoS = com.Procarihana.AccountingService.Moudle.service.UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();
        val result = userInfoCToSeConverter.reverse().convert(userInfoS);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("username", username);
    }
}
