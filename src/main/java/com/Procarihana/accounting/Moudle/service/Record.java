package com.procarihana.accounting.moudle.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String note;
    private String category;
    private List<Tag> tagList;
}
