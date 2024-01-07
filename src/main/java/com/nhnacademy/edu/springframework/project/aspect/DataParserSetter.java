package com.nhnacademy.edu.springframework.project.aspect;

import com.nhnacademy.edu.springframework.project.parser.DataParser;
import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataParserSetter{
    public DataParserSetter(@Qualifier("defaultWaterBillChartRepository") WaterBillChartRepository waterBillChartRepository,@Qualifier("csvDataParser") DataParser csvDataParser, @Qualifier("jsonDataParser") DataParser jsonDataParser) {
        this.waterBillChartRepository = waterBillChartRepository;
        this.csvDataParser = csvDataParser;
        this.jsonDataParser = jsonDataParser;
    }

    private final WaterBillChartRepository waterBillChartRepository;

    private final DataParser csvDataParser;

    private final DataParser jsonDataParser;
    @Before("execution(* com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository.load(..)) && args(filePath)") // @Before("execution(* set*(.
    public void setDataParser(String filePath){
        if(filePath.endsWith("csv")){
            waterBillChartRepository.setDataParser(csvDataParser);
        }else if (filePath.endsWith("json")){
            waterBillChartRepository.setDataParser(jsonDataParser);
        }else {
            throw new IllegalArgumentException("파일 확장자가 잘못되었습니다.");
        }
    }
}
