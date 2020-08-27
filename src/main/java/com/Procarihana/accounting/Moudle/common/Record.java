package com.procarihana.accounting.moudle.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private String recordId;
    private BigDecimal amount;
    private String notes;
    private String category;
    private List<String> tagList;
    private LocalDate creationDate;
    private LocalDate lastUpdateDate;
    private String createdBy;
    private String lastUpdateBy;
}
