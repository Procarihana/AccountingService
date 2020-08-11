package com.Procarihana.AccountingService.Moudle.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
