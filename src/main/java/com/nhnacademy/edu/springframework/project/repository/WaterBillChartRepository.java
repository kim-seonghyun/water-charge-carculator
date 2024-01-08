package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.parser.DataParser;

import java.util.List;

public interface WaterBillChartRepository {


    /**
     * Load water tariff data from file
     * @param filePath notNull, notEmpty
     */
    void load(String filePath);

    /**
     * @param usage > 0
     * @return notNull WaterTariffList
     */
    List<WaterBillInfo> getWaterBillByUsage(long usage);
    void setDataParser(DataParser dataParser);

    void isLoaded();
}
