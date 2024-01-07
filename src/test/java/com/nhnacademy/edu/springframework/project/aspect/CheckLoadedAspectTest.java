package com.nhnacademy.edu.springframework.project.aspect;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CheckLoadedAspectTest {
    @InjectMocks
    private CheckLoadedAspect checkLoadedAspect;

    @Mock
    private WaterBillChartRepository waterBillChartRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkLoaded() {
        doNothing().when(waterBillChartRepository).isLoaded();

        checkLoadedAspect.checkLoaded();

        verify(waterBillChartRepository).isLoaded();
    }
}