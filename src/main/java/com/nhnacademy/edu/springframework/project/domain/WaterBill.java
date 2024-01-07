package com.nhnacademy.edu.springframework.project.domain;

import java.util.Objects;

public class WaterBill {
    private final String city;

    private final String sector;

    private final Long unitPrice;

    private final long billTotal;

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public long getBillTotal() {
        return billTotal;
    }

    public WaterBill(String city, String sector, long unitPrice, long billTotal) {
        if (Objects.isNull(city)) {
            throw new IllegalArgumentException("city는 null이 될 수 없습니다.");
        }
        if (Objects.isNull(sector)) {
            throw new IllegalArgumentException("sector는 null이 될 수 없습니다.");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("unitPrice는 0보다 작을 수 없습니다.");
        }
        if (billTotal < 0) {
            throw new IllegalArgumentException("billTotal은 0보다 작을 수 없습니다.");
        }
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterBill waterBill = (WaterBill) o;
        return billTotal == waterBill.billTotal && Objects.equals(city, waterBill.city)
                && Objects.equals(sector, waterBill.sector) && Objects.equals(unitPrice,
                waterBill.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, sector, unitPrice, billTotal);
    }
}
