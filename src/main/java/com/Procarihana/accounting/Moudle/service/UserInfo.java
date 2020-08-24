package com.procarihana.accounting.moudle.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//忽略为空的字段
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
