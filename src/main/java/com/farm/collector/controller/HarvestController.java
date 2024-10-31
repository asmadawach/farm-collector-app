package com.farm.collector.controller;

import com.farm.collector.dto.request.HarvestDto;
import com.farm.collector.service.HarvestService;
import com.farm.collector.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    /**
     * Endpoint to add harvest data.
     *
     * This method receives harvest details and processes them to save the corresponding
     * harvest record. It interacts with the harvest service to perform the necessary
     * operations.
     *
     * @param harvestDto the data transfer object containing harvest details
     * @return a response entity containing a success message
     * @throws RuntimeException if the specified plant ID does not exist
     */
    @PostMapping(CommonConstants.HARVESTING)
    public ResponseEntity<String> harvesting(@RequestBody HarvestDto harvestDto) {
        harvestService.harvesting(harvestDto);
        return ResponseEntity.ok("Harvest data saved successfully.");
    }
}
