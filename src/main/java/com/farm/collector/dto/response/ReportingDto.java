package com.farm.collector.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportingDto {
    private String farmName;
    private String fieldName;
    private String cropName;
    private String seasonName;
    private double expectedProductAmount;
    private double actualHarvestedAmount;
    private double plantingArea;
}
