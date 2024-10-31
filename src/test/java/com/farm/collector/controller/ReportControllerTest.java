package com.farm.collector.controller;

import com.farm.collector.dto.response.ReportingDto;
import com.farm.collector.service.ReportingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportingService reportingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReporting() {
        // Given
        ReportingDto report1 = new ReportingDto();

        ReportingDto report2 = new ReportingDto();

        List<ReportingDto> expectedReports = Arrays.asList(report1, report2);

        when(reportingService.reporting()).thenReturn(expectedReports);

        // When
        ResponseEntity<List<ReportingDto>> response = reportController.reporting();

        // Then
        verify(reportingService).reporting();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReports, response.getBody());
    }
}
