package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.service.WaterBillReport;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultWaterBillReport implements WaterBillReport {
    @Override
    public String generateReport(List<WaterBill> waterBill) {
        final int LIMIT_SIZE = 5;

        return waterBill.stream().sorted(Comparator.comparingLong(WaterBill::getBillTotal)).limit(LIMIT_SIZE)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
