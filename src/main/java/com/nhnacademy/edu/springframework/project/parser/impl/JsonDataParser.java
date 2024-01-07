package com.nhnacademy.edu.springframework.project.parser.impl;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.parser.DataParser;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JsonDataParser implements DataParser {
    private List<WaterBillInfo> waterBillInfos;

    public JsonDataParser() {
        waterBillInfos = new ArrayList<>();
    }

    @Override
    public List<WaterBillInfo> parse(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(requireNonNull(ClassLoader.getSystemResource(filePath)).getPath());

        try {
            waterBillInfos = Arrays.asList(objectMapper.readValue(file, WaterBillInfo[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return waterBillInfos;
    }

}
