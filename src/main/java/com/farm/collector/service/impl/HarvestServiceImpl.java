package com.farm.collector.service.impl;

import com.farm.collector.dto.request.HarvestDto;
import com.farm.collector.entities.Harvest;
import com.farm.collector.repository.HarvestRepository;
import com.farm.collector.repository.PlantRepository;
import com.farm.collector.service.HarvestService;
import com.farm.collector.utils.FarmCollectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private FarmCollectorUtil farmCollectorUtil;

    /**
     * Processes the harvesting of a plant based on the provided HarvestDto.
     *
     * This method performs the following steps:
     * 1. Converts the HarvestDto to a Harvest entity.
     * 2. Retrieves the corresponding Plant entity using the provided plant ID from the HarvestDto.
     * 3. Sets the retrieved Plant entity to the Harvest entity.
     * 4. Saves the Harvest entity to the database.
     *
     * @param harvestDto the data transfer object containing information about the harvest to be processed.
     * @throws IllegalArgumentException if the provided plant ID does not correspond to an existing Plant entity.
     */
    @Override
    public void harvesting(HarvestDto harvestDto) {
        log.info("add harvesting data");
        Harvest harvest = Harvest.toEntity(harvestDto);

        harvest.setPlant(farmCollectorUtil.getEntityOrThrow(plantRepository,
                harvestDto.getPlantId(), "Plant not found for plant id "));

        harvestRepository.save(harvest);
    }
}
