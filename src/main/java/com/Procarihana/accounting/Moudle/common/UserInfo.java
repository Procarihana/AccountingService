package com.procarihana.accounting.moudle.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
