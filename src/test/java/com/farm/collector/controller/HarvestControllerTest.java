package com.farm.collector.controller;

import com.farm.collector.dto.request.HarvestDto;
import com.farm.collector.service.HarvestService;
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

class HarvestControllerTest {

    @InjectMocks
    private HarvestController harvestController;

    @Mock
    private HarvestService harvestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHarvestingSuccess() {
        // Given
        HarvestDto harvestDto = new HarvestDto();

        // When
        ResponseEntity<String> response = harvestController.harvesting(harvestDto);

        // Then
        verify(harvestService).harvesting(harvestDto);
        assertEquals("Harvest data saved successfully.", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testHarvestingPlantNotFound() {
        // Given
        HarvestDto harvestDto = new HarvestDto();

        doThrow(new RuntimeException("Plant not found for plant id ..."))
                .when(harvestService).harvesting(any());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            harvestController.harvesting(harvestDto);
        });

        assertEquals("Plant not found for plant id ...", exception.getMessage());
    }
}

