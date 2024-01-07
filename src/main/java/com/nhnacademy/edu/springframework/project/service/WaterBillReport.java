package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;

import java.util.List;

public interface WaterBillReport {
    String generateReport(List<WaterBill> waterBillInfos);
}
