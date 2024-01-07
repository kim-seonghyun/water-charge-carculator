package com.nhnacademy.edu.springframework.project.parser.impl;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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