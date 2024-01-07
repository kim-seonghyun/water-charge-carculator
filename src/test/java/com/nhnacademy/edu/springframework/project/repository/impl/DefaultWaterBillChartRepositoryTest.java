package com.nhnacademy.edu.springframework.project.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.parser.DataParser;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class DefaultWaterBillChartRepositoryTest {
    @InjectMocks
    private DefaultWaterBillChartRepository defaultWaterBillChartRepository;

    @Mock
    private DataParser dataParser;

    @Mock
    List<WaterBillInfo> waterBillInfos;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void isLoaded() {
        when(waterBillInfos.isEmpty()).thenReturn(false);

        defaultWaterBillChartRepository.isLoaded();

        verify(waterBillInfos).isEmpty();
    }

    @Test
    void load() {
        doNothing().when(waterBillInfos).clear();
        when(dataParser.parse("testFile")).thenReturn(waterBillInfos);

        defaultWaterBillChartRepository.load("testFile");

        verify(dataParser).parse(anyString());

    }

    @Test
    void getWaterBillByUsage() {
        final int EXPECTED_SIZE = 2;
        final int RANGE_END_SUCCESS = 100;
        final int RANGE_END_SUCCESS2 = 50;
        final int RANGE_END_FAIL = 20;
        List<WaterBillInfo> testWaterBillInfos = new ArrayList<>();
        testWaterBillInfos.add(new WaterBillInfo(1, "도시", "업종", 1, 1, RANGE_END_SUCCESS, 1L, 1L));
        testWaterBillInfos.add(new WaterBillInfo(1, "도시", "업종", 1, 1, RANGE_END_SUCCESS2, 1L, 1L));
        testWaterBillInfos.add(new WaterBillInfo(1, "도시", "업종", 1, 1, RANGE_END_FAIL, 1L, 1L));
        ReflectionTestUtils.setField(defaultWaterBillChartRepository, "waterBillInfos", testWaterBillInfos);

        List<WaterBillInfo> result = defaultWaterBillChartRepository.getWaterBillByUsage(30);

        assertEquals(EXPECTED_SIZE, result.size());

    }
}