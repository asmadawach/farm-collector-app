package com.farm.collector.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDto {
    @NonNull
    private Long plantId;
    @NonNull
    private double actualHarvestedAmount;
}
