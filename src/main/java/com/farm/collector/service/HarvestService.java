package com.farm.collector.service;


import com.farm.collector.dto.request.HarvestDto;

public interface HarvestService {
    void harvesting(HarvestDto harvestDto);
}
