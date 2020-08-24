package com.procarihana.accounting.moudle.presistence;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
