package com.nhnacademy.edu.springframework.project.repository.impl;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.parser.DataParser;
import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DefaultWaterBillChartRepository implements WaterBillChartRepository {


    @Qualifier("jsonDataParser")
    private DataParser dataParser;

    private List<WaterBillInfo> waterBillInfos;
    public DefaultWaterBillChartRepository(){
        waterBillInfos = new ArrayList<>();
    }

    /**
     * @param dataParser not null
     */
    public void setDataParser(DataParser dataParser) {
        if(Objects.isNull(dataParser)){
            throw new IllegalArgumentException("dataParser는 null이 될 수 없습니다.");
        }
        this.dataParser = dataParser;
    }


    @Override
    public void isLoaded() {
        if (this.waterBillInfos.isEmpty()) {
            throw new IllegalStateException("load가 완료되지 않았습니다.");
        }
    }


    @Override
    public void load(String filePath) {
        this.waterBillInfos.clear();
        waterBillInfos = dataParser.parse(filePath);
    }

    @Override
    public List<WaterBillInfo> getWaterBillByUsage(long usage) {
        return waterBillInfos.stream().filter(waterBillInfo -> waterBillInfo.isInRange(usage)).collect(Collectors.toList());
    }
}
