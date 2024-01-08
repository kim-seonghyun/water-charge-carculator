package com.nhnacademy.edu.springframework.project.parser.impl;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvDataParserTest {
    private CsvDataParser csvDataParser;


    @BeforeEach
    void setUp() {
        csvDataParser = new CsvDataParser();
    }

    @Test
    void parse() {
        final int EXPECTED_SIZE = 2;
        List<WaterBillInfo> waterBillInfos = csvDataParser.parse("data/csvTest.csv");
        Assertions.assertEquals(EXPECTED_SIZE, waterBillInfos.size());
    }



}