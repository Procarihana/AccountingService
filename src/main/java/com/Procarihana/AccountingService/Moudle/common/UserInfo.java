package com.Procarihana.AccountingService.Moudle.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private LocalDate createTime;
    private LocalDate updateTime;
}
