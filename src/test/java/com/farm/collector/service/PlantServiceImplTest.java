package com.farm.collector.service;

import com.farm.collector.dto.request.PlantDto;
import com.farm.collector.entities.*;
import com.farm.collector.repository.CropRepository;
import com.farm.collector.repository.FieldRepository;
import com.farm.collector.repository.PlantRepository;
import com.farm.collector.repository.SeasonRepository;
import com.farm.collector.service.impl.PlantServiceImpl;
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

public class PlantServiceImplTest {

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private CropRepository cropRepository;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmCollectorUtil farmCollectorUtil;

    @InjectMocks
    private PlantServiceImpl plantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlanting() {
        // Given
        PlantDto plantDto = new PlantDto();
        plantDto.setSeasonId(1L);
        plantDto.setCropId(2L);
        plantDto.setFieldId(3L);

        Season season = new Season();
        season.setSeasonId(1L);
        Crop crop = new Crop();
        crop.setCropId(2L);
        Field field = new Field();
        field.setFieldId(3L);

        when(farmCollectorUtil.getEntityOrThrow(seasonRepository, 1L, "Season not found for season id "))
                .thenReturn(season);
        when(farmCollectorUtil.getEntityOrThrow(cropRepository, 2L, "Crop not found for crop id "))
                .thenReturn(crop);
        when(farmCollectorUtil.getEntityOrThrow(fieldRepository, 3L, "Field not found for field id "))
                .thenReturn(field);

        // When
        plantService.planting(plantDto);

        // Then
        verify(plantRepository, times(1)).save(any(Plant.class));
    }

    @Test
    public void testPlantingWithInvalidSeason() {
        // Given
        PlantDto plantDto = new PlantDto();
        plantDto.setSeasonId(1L);
        plantDto.setCropId(2L);
        plantDto.setFieldId(3L);

        when(farmCollectorUtil.getEntityOrThrow(seasonRepository, 1L, "Season not found for season id "))
                .thenThrow(new IllegalArgumentException("Season not found for season id "));

        // When & Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            plantService.planting(plantDto);
        });

        assertEquals("Season not found for season id ", thrown.getMessage());
        verify(plantRepository, never()).save(any());
    }
}

