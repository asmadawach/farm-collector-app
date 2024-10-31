package com.farm.collector.controller;

import com.farm.collector.dto.request.PlantDto;
import com.farm.collector.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PlantControllerTest {

    @InjectMocks
    private PlantController plantController;

    @Mock
    private PlantService plantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlantingSuccess() {
        // Given
        PlantDto plantDto = new PlantDto();

        // When
        ResponseEntity<String> response = plantController.planting(plantDto);

        // Then
        verify(plantService).planting(plantDto);
        assertEquals("Plant data saved successfully.", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testPlantingSeasonNotFound() {
        // Given
        PlantDto plantDto = new PlantDto();

        doThrow(new RuntimeException("Season not found for season id ..."))
                .when(plantService).planting(any());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            plantController.planting(plantDto);
        });

        assertEquals("Season not found for season id ...", exception.getMessage());
    }

    @Test
    void testPlantingCropNotFound() {
        // Given
        PlantDto plantDto = new PlantDto();

        doThrow(new RuntimeException("Crop not found for crop id ..."))
                .when(plantService).planting(any());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            plantController.planting(plantDto);
        });

        assertEquals("Crop not found for crop id ...", exception.getMessage());
    }

    @Test
    void testPlantingFieldNotFound() {
        // Given
        PlantDto plantDto = new PlantDto();

        doThrow(new RuntimeException("Field not found for field id ..."))
                .when(plantService).planting(any());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            plantController.planting(plantDto);
        });

        assertEquals("Field not found for field id ...", exception.getMessage());
    }
}

