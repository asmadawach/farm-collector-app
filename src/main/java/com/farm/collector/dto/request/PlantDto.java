package com.farm.collector.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantDto {
    @NonNull
    private Long fieldId;
    @NonNull
    private Long cropId;
    @NonNull
    private Long seasonId;
    @NonNull
    private double plantingArea;
    @NonNull
    private double expectedProductAmount;
}
