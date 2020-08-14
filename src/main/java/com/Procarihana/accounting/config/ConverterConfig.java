package com.procarihana.accounting.config;

import com.procarihana.accounting.converter.commonToService.UserInfoCToSeConverter;
import com.procarihana.accounting.converter.presisitenceToCommon.UserInfoPresToComConverter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConverterConfig {
    @Bean
    public UserInfoPresToComConverter userInfoPresToComConverter() {
        return new UserInfoPresToComConverter();
    }

    @Bean
    @ConfigurationPropertiesBinding
    public UserInfoCToSeConverter userInfoCToSeConverter() {
        return new UserInfoCToSeConverter();
    }
}
