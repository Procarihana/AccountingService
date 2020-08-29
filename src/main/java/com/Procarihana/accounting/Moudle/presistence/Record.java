package com.procarihana.accounting.moudle.presistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Integer category;
    private List<Tag> tagList;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateDate;
    private Integer status;
}
