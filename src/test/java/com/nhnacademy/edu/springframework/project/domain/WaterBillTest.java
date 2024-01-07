package com.nhnacademy.edu.springframework.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterBillTest {

    private WaterBill testWaterBill;

    @BeforeEach
    void setUp() {
        testWaterBill = new WaterBill("지자체명", "업종", 1L, 1L);
    }

    @Test
    void classInvariant() {
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBill(null, "업종", 1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBill("지자체명", null, 1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBill("지자체명", "업종", -1L, 1L);
        });
        Assertions.assertThrows((IllegalArgumentException.class), () -> {
            new WaterBill("지자체명", "업종", 1L, -1L);
        });
    }

    @Test
    void getCity() {
        Assertions.assertEquals("지자체명", testWaterBill.getCity());
    }

    @Test
    void getSector() {
        Assertions.assertEquals("업종", testWaterBill.getSector());
    }

    @Test
    void getUnitPrice() {
        Assertions.assertEquals(1L, testWaterBill.getUnitPrice());
    }

    @Test
    void getBillTotal() {
        Assertions.assertEquals(1L, testWaterBill.getBillTotal());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("WaterBill{city='지자체명', sector='업종', unitPrice=1, billTotal=1}",
                testWaterBill.toString());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(new WaterBill("지자체명", "업종", 1L, 1L), testWaterBill);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(new WaterBill("지자체명", "업종", 1L, 1L).hashCode(), testWaterBill.hashCode());
    }
}