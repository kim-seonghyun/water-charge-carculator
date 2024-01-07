package com.nhnacademy.edu.springframework.project.aspect;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DataParserSetterTest {
    @InjectMocks
    private DataParserSetter dataParserSetter;
    @Mock
    private WaterBillChartRepository waterBillChartRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("csv 테스트")
    void setDataParser() {
        doNothing().when(waterBillChartRepository).setDataParser(any());

        dataParserSetter.setDataParser("testFilePath.csv");

        verify(waterBillChartRepository).setDataParser(any());
    }

    @Test
    @DisplayName("json 테스트")
    void setDataParser2() {
        doNothing().when(waterBillChartRepository).setDataParser(any());

        dataParserSetter.setDataParser("testFilePath.json");

        verify(waterBillChartRepository).setDataParser(any());
    }

    @Test
    @DisplayName("파일 확장자가 잘못됐을 경우 ")
    void setDataParser3() {
        doNothing().when(waterBillChartRepository).setDataParser(any());

        assertThrows(IllegalArgumentException.class, () -> dataParserSetter.setDataParser("testFilePath.txt"));
    }
}