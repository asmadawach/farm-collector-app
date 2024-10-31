package com.farm.collector.service.impl;

import com.farm.collector.dto.response.ReportingDto;
import com.farm.collector.entities.*;
import com.farm.collector.repository.PlantRepository;
import com.farm.collector.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {
    @Autowired
    private PlantRepository plantRepository;

    /**
     * Generates a report of all plants in the repository, transforming each Plant entity into a ReportingDto.
     *
     * This method retrieves all Plant entities, and for each plant, it constructs a ReportingDto with the following information:
     * - Crop name
     * - Field name
     * - Season name
     * - Farm name
     * - Expected product amount
     * - Actual harvested amount
     * - Planting area
     *
     * It handles possible null values gracefully using Optional to avoid NullPointerExceptions.
     *
     * @return a list of ReportingDto objects containing the reporting information for all plants.
     */
    @Override
    public List<ReportingDto> reporting() {
        return plantRepository.findAll().stream()
                .map(plant -> ReportingDto.builder()
                        .cropName(Optional.ofNullable(plant.getCrop()).map(Crop::getCropName).orElse(null))
                        .fieldName(Optional.ofNullable(plant.getField()).map(Field::getFieldName).orElse(null))
                        .seasonName(Optional.ofNullable(plant.getSeason()).map(Season::getSeasonName).orElse(null))
                        .farmName(Optional.ofNullable(plant.getField())
                                .map(Field::getFarm)
                                .map(Farm::getFarmName)
                                .orElse(null))
                        .expectedProductAmount(plant.getExpectedProductAmount())
                        .actualHarvestedAmount(Optional.ofNullable(plant.getHarvest())
                                .map(Harvest::getActualHarvestedAmount).orElse(0.0))
                        .plantingArea(plant.getPlantingArea())
                        .build())
                .collect(Collectors.toList());
    }
}
