package com.nhnacademy.edu.springframework.project.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterBillInfoTest {
    private WaterBillInfo testWaterBillInfo;
    @Test
    void classInvariant() {
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBillInfo(1, null, "업종", 1, 1, 1, 1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBillInfo(1, "지자체명", null, 1, 1, 1, 1L, 1L);
        });

        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBillInfo(1, "지자체명", "업종", 1, -1, 1, 1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBillInfo(1, "지자체명", "업종", 1, 1, -1, 1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBillInfo(1, "지자체명", "업종", -1, 1, 1, 1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBillInfo(1, "지자체명", "업종", 1, 1, 1, -1L, 1L);
        });

    }
    @Test
    void isInRange() {
        final int RANGE_START = 2;
        final int RANGE_END = 10;
        WaterBillInfo waterBillInfo = new WaterBillInfo(1, "지자체명", "업종", 1, RANGE_START, RANGE_END, 1L, 1L);
        assertTrue(waterBillInfo.isInRange(RANGE_START));
        assertTrue(waterBillInfo.isInRange(RANGE_END));
        assertFalse(waterBillInfo.isInRange(RANGE_START - 1));
        assertFalse(waterBillInfo.isInRange(RANGE_END + 1));
    }

    @BeforeEach
    void setUp() {
        testWaterBillInfo = new WaterBillInfo(1, "지자체명", "업종", 1, 1, 1, 1L, 1L);
    }

    @Test
    void getSequenceNumber() {
        Assertions.assertEquals(1, testWaterBillInfo.getSequenceNumber());
    }

    @Test
    void getCity() {
        Assertions.assertEquals("지자체명", testWaterBillInfo.getCity());
    }

    @Test
    void getSector() {
        Assertions.assertEquals("업종", testWaterBillInfo.getSector());
    }

    @Test
    void getStage() {
        Assertions.assertEquals(1, testWaterBillInfo.getStage());
    }


    @Test
    void testEquals() {
        Assertions.assertEquals(new WaterBillInfo(1, "지자체명", "업종", 1, 1, 1, 1L, 1L), testWaterBillInfo);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(new WaterBillInfo(1, "지자체명", "업종", 1, 1, 1, 1L, 1L).hashCode(), testWaterBillInfo.hashCode());
    }

    @Test
    void getUnitPrice() {
        Assertions.assertEquals(1L, testWaterBillInfo.getUnitPrice());
    }

    @Test
    void getBaseFeePerStageInWon() {
        Assertions.assertEquals(1L, testWaterBillInfo.getBaseFeePerStageInWon());
    }
}