package com.nhnacademy.edu.springframework.project.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DefaultWaterBillReportTest {
    @InjectMocks
    private DefaultWaterBillReport defaultWaterBillReport;


    private List<WaterBill> waterBills;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        waterBills = new ArrayList<>();
        waterBills.add(new WaterBill("지자체명", "업종", 1L, 1L));
        waterBills.add(new WaterBill("지자체명", "업종", 1L, 2L));
        waterBills.add(new WaterBill("지자체명", "업종", 1L, 3L));
        waterBills.add(new WaterBill("지자체명", "업종", 1L, 4L));
        waterBills.add(new WaterBill("지자체명", "업종", 1L, 5L));
        waterBills.add(new WaterBill("출력되지않는도시", "출력되지않는업종", 1L, 6L));
    }

    @Test
    void generateReport() {
        String result = defaultWaterBillReport.generateReport(waterBills);
        assertFalse(result.contains("출력되지않는도시"));
        assertFalse(result.contains("출력되지않는업종"));
    }
}