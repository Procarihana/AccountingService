package com.Procarihana.AccountingService.config;

import com.Procarihana.AccountingService.converter.commonToService.UserInfoCToSeConverter;
import com.Procarihana.AccountingService.converter.presisitenceToCommon.UserInfoPresToComConverter;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConverterConfig {
    @Bean
    @ConfigurationPropertiesBinding
    public UserInfoPresToComConverter userInfoPresToComConverter() {
        return new UserInfoPresToComConverter();
    }

    @Bean
    @ConfigurationPropertiesBinding
    public UserInfoCToSeConverter userInfoCToSeConverter() {
        return new UserInfoCToSeConverter();
    }
}
