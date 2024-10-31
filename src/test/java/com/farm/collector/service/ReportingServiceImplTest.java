package com.farm.collector.service;

import com.farm.collector.dto.response.ReportingDto;
import com.farm.collector.entities.*;
import com.farm.collector.repository.PlantRepository;
import com.farm.collector.service.impl.ReportingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReportingServiceImplTest {

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private ReportingServiceImpl reportingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReporting() {
        // Given
        Crop crop = new Crop();
        crop.setCropName("Wheat");

        Field field = new Field();
        field.setFieldName("Field A");

        Farm farm = new Farm();
        farm.setFarmName("Farm 1");
        field.setFarm(farm);

        Season season = new Season();
        season.setSeasonName("Spring");

        Harvest harvest = new Harvest();
        harvest.setActualHarvestedAmount(100.0);

        Plant plant1 = new Plant();
        plant1.setCrop(crop);
        plant1.setField(field);
        plant1.setSeason(season);
        plant1.setExpectedProductAmount(150.0);
        plant1.setPlantingArea(500.0);
        plant1.setHarvest(harvest);

        Plant plant2 = new Plant();
        plant2.setCrop(null); // No crop
        plant2.setField(field);
        plant2.setSeason(season);
        plant2.setExpectedProductAmount(200.0);
        plant2.setPlantingArea(700.0);
        plant2.setHarvest(null); // No harvest

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));

        // When
        List<ReportingDto> report = reportingService.reporting();

        // Then
        assertEquals(2, report.size());

        ReportingDto dto1 = report.get(0);
        assertEquals("Wheat", dto1.getCropName());
        assertEquals("Field A", dto1.getFieldName());
        assertEquals("Spring", dto1.getSeasonName());
        assertEquals("Farm 1", dto1.getFarmName());
        assertEquals(150.0, dto1.getExpectedProductAmount());
        assertEquals(100.0, dto1.getActualHarvestedAmount());
        assertEquals(500.0, dto1.getPlantingArea());

        ReportingDto dto2 = report.get(1);
        assertEquals(null, dto2.getCropName());
        assertEquals("Field A", dto2.getFieldName());
        assertEquals("Spring", dto2.getSeasonName());
        assertEquals("Farm 1", dto2.getFarmName());
        assertEquals(200.0, dto2.getExpectedProductAmount());
        assertEquals(0.0, dto2.getActualHarvestedAmount());
        assertEquals(700.0, dto2.getPlantingArea());
    }
}
