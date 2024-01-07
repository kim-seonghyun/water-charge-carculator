package com.nhnacademy.edu.springframework.project.parser.impl;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import com.nhnacademy.edu.springframework.project.parser.DataParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CsvDataParser implements DataParser {


    private final List<WaterBillInfo> waterBillInfos;

    public CsvDataParser() {
        waterBillInfos = new ArrayList<>();
    }

    /**
     * csv 파일을 parsing하여 WaterBillInfo 객체를 생성한다.
     * @param filePath notNull, notEmpty
     * @return notNull
     */
    @Override
    public List<WaterBillInfo> parse(String filePath) {
        final int ARGUMENTS_SIZE = 8;

        if (filePath.isEmpty()) {
            throw new IllegalArgumentException("파일 경로가 비어있습니다.");
        }
        if (Objects.isNull(filePath)) {
            throw new IllegalArgumentException("파일 경로가 null입니다.");
        }
        String actualPath = ClassLoader.getSystemResource(filePath).getPath();
        File csvFile = new File(Objects.requireNonNull(actualPath));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.lastIndexOf(",") == line.length() - 1) {
                    line = line + "-1";
                }
                List<String> arguments = List.of(line.split(","));

                if (arguments.size() < ARGUMENTS_SIZE) {
                    throw new IllegalArgumentException(ARGUMENTS_SIZE + "개의 매개변수가 필요합니다");
                }
                List<String> trimedArguments = arguments.stream().map(String::trim).collect(Collectors.toList());
                trimedArguments.forEach(s -> {
                    if (s.isEmpty()) {
                        throw new IllegalArgumentException("매개변수는 최소 한자가 필요합니다");
                    }
                });
                WaterBillInfo waterBillInfo = getWaterBillInfo(trimedArguments);
                waterBillInfos.add(waterBillInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 parsing 하는 도중 에러가 발생했습니다.");
        }
        return waterBillInfos;
    }

    private static WaterBillInfo getWaterBillInfo(List<String> trimedArguments) {
        final int sequenceNumber = Integer.parseInt(trimedArguments.get(0));
        final String localGovernment = trimedArguments.get(1);
        final String industry = trimedArguments.get(2);
        final int stage = Integer.parseInt(trimedArguments.get(3));
        final int rangeStart = Integer.parseInt(trimedArguments.get(4));
        final int rangeEnd = Integer.parseInt(trimedArguments.get(5));
        final long unitPrice = Long.parseLong(trimedArguments.get(6));
        final Long baseFeePerStageInWon = Long.parseLong(trimedArguments.get(7));
        return new WaterBillInfo(sequenceNumber, localGovernment, industry, stage,
                rangeStart, rangeEnd, unitPrice, baseFeePerStageInWon);
    }
}


