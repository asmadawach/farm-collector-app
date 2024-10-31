package com.farm.collector.service.impl;

import com.farm.collector.dto.request.PlantDto;
import com.farm.collector.entities.Plant;
import com.farm.collector.repository.CropRepository;
import com.farm.collector.repository.FieldRepository;
import com.farm.collector.repository.PlantRepository;
import com.farm.collector.repository.SeasonRepository;
import com.farm.collector.service.PlantService;
import com.farm.collector.utils.FarmCollectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlantServiceImpl implements PlantService {

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FarmCollectorUtil farmCollectorUtil;

    /**
     * Handles the planting of a new plant based on the provided PlantDto.
     *
     * This method performs the following steps:
     * 1. Converts the PlantDto to a Plant entity.
     * 2. Retrieves the corresponding Season, Crop, and Field entities using their IDs from the PlantDto.
     * 3. Sets the retrieved Season, Crop, and Field entities to the Plant entity.
     * 4. Saves the Plant entity to the database.
     *
     * @param plantDto the data transfer object containing information about the plant to be planted.
     * @throws IllegalArgumentException if the provided season, crop, or field IDs do not correspond to existing entities.
     */
    @Override
    public void planting(PlantDto plantDto) {
        log.info("add planting data");
        Plant plant = Plant.toEntity(plantDto);

        plant.setSeason(farmCollectorUtil.getEntityOrThrow(seasonRepository, plantDto.getSeasonId(),
                "Season not found for season id "));
        plant.setCrop(farmCollectorUtil.getEntityOrThrow(cropRepository, plantDto.getCropId(),
                "Crop not found for crop id "));
        plant.setField(farmCollectorUtil.getEntityOrThrow(fieldRepository, plantDto.getFieldId(),
                "Field not found for field id "));

        plantRepository.save(plant);
    }
}
