package com.farm.collector.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmCollectorErrorRes {
    private Integer httpStatus;
    private String message;
}
