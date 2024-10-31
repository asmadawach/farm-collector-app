package com.farm.collector.service;

import com.farm.collector.dto.request.HarvestDto;
import com.farm.collector.entities.Harvest;
import com.farm.collector.entities.Plant;
import com.farm.collector.repository.HarvestRepository;
import com.farm.collector.repository.PlantRepository;
import com.farm.collector.service.impl.HarvestServiceImpl;
import com.farm.collector.utils.FarmCollectorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HarvestServiceImplTest {

    @Mock
    private HarvestRepository harvestRepository;

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private FarmCollectorUtil farmCollectorUtil;

    @InjectMocks
    private HarvestServiceImpl harvestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHarvesting() {
        // Given
        HarvestDto harvestDto = new HarvestDto();
        harvestDto.setPlantId(1L);

        Plant plant = new Plant();
        plant.setPlantId(1L);

        when(farmCollectorUtil.getEntityOrThrow(plantRepository, 1L, "Plant not found for plant id "))
                .thenReturn(plant);

        // When
        harvestService.harvesting(harvestDto);

        // Then
        verify(harvestRepository, times(1)).save(any(Harvest.class));
    }

    @Test
    public void testHarvestingWithInvalidPlant() {
        // Given
        HarvestDto harvestDto = new HarvestDto();
        harvestDto.setPlantId(1L);

        when(farmCollectorUtil.getEntityOrThrow(plantRepository, 1L, "Plant not found for plant id "))
                .thenThrow(new IllegalArgumentException("Plant not found for plant id "));

        // When & Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            harvestService.harvesting(harvestDto);
        });

        assertEquals("Plant not found for plant id ", thrown.getMessage());
        verify(harvestRepository, never()).save(any());
    }
}

