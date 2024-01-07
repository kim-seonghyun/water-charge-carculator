package com.nhnacademy.edu.springframework.project.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DefaultWaterBillServiceTest {

    @InjectMocks
    private DefaultWaterBillService defaultWaterBillService;

    @Mock
    private WaterBillChartRepository waterBillChartRepository;

    private List<WaterBillInfo> waterBillInfos;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        waterBillInfos = new ArrayList<>();
        waterBillInfos.add(new WaterBillInfo(1, "지자체명", "업종", 1, 500, 1500, 100, 1L));
    }

    @Test
    void calculateWaterBill() {
        List<WaterBill> waterBills;
        when(waterBillChartRepository.getWaterBillByUsage(anyLong())).thenReturn(waterBillInfos);

        waterBills = defaultWaterBillService.calculateWaterBill(1000);

        verify(waterBillChartRepository).getWaterBillByUsage(anyLong());
        Assertions.assertEquals(10_0000, waterBills.get(0).getBillTotal());

    }
}