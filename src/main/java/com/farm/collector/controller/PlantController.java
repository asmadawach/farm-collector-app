package com.farm.collector.controller;

import com.farm.collector.dto.request.PlantDto;
import com.farm.collector.service.PlantService;
import com.farm.collector.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PlantController {

    @Autowired
    private PlantService plantService;

    /**
     * Endpoint to add planting data.
     *
     * @param plantDto the data transfer object containing planting details
     * @return a response entity containing a success message
     * @throws RuntimeException if the specified season ID does not exist
     * @throws RuntimeException if the specified crop ID does not exist
     * @throws RuntimeException if the specified field ID does not exist
     */
    @PostMapping(CommonConstants.PLANTING)
    public ResponseEntity<String> planting(@RequestBody PlantDto plantDto) {
        plantService.planting(plantDto);
        return ResponseEntity.ok("Plant data saved successfully.");
    }

}
