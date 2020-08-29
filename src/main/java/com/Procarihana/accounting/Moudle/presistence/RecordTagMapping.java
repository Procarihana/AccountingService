package com.procarihana.accounting.moudle.presistence;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordTagMapping {
    private Long recordId;
    private Long tagId;
    private Integer status;
}
