package com.farm.collector.controller;

import com.farm.collector.dto.response.ReportingDto;
import com.farm.collector.service.ReportingService;
import com.farm.collector.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private ReportingService reportingService;

    /**
     * Endpoint to retrieve reporting data.
     *
     * This method fetches all the reporting information related to the plants, crops, fields,
     * and seasons. It utilizes the reporting service to gather the necessary data and returns
     * it as a list of ReportingDto objects.
     *
     * @return a response entity containing a list of ReportingDto objects with report data
     */
    @GetMapping(CommonConstants.REPORTING)
    @Operation(summary = "Get all season reports")
    public ResponseEntity<List<ReportingDto>> getAllPlantingAndHarvestingData() {
        return ResponseEntity.ok(reportingService.getAllPlantingAndHarvestingData());
    }
}
