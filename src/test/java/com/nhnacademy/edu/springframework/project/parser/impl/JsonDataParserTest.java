package com.nhnacademy.edu.springframework.project.parser.impl;

import static java.util.Objects.requireNonNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonDataParserTest {
    private JsonDataParser jsonDataParser;


    @BeforeEach
    void setUp() {
        jsonDataParser = new JsonDataParser();
    }

    @Test
    void parse() {
        final int EXPECTED_SIZE = 2;
        List<WaterBillInfo> waterBillInfos = jsonDataParser.parse("data/jsonTest.json");
        Assertions.assertEquals(EXPECTED_SIZE, waterBillInfos.size());
    }
}