package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import com.nhnacademy.edu.springframework.project.service.WaterBillService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultWaterBillService implements WaterBillService {
    private final WaterBillChartRepository waterBillChartRepository;


    @Autowired
    public DefaultWaterBillService(WaterBillChartRepository waterBillChartRepository) {
        this.waterBillChartRepository = waterBillChartRepository;
    }


    @Override
    public List<WaterBill> calculateWaterBill(long usage) {
        List<WaterBill> waterBills = new ArrayList<>();
        List<WaterBillInfo> waterBillInfos = waterBillChartRepository.getWaterBillByUsage(usage);
        waterBillInfos.forEach(waterBillInfo -> waterBills.add(
                new WaterBill(waterBillInfo.getCity(), waterBillInfo.getSector(), waterBillInfo.getUnitPrice(),
                        waterBillInfo.getUnitPrice() * usage)));
        return waterBills;
    }
}
