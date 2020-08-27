package com.procarihana.accounting.moudle.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private Long userId;
    private String description;
    private String status;
}
