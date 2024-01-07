package com.nhnacademy.edu.springframework.project.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class WaterBillInfo {
    private final int sequenceNumber;
    private final String city;

    private final String sector;

    private final int stage;


    private final long rangeStart;

    private final long rangeEnd;

    private final long unitPrice;

    private final Long baseFeePerStageInWon;

    @JsonCreator
    public WaterBillInfo(@JsonProperty("순번") int sequenceNumber,
                         @JsonProperty("지자체명") String city,
                         @JsonProperty("업종") String sector,
                         @JsonProperty("단계") int stage,
                         @JsonProperty("구간시작(세제곱미터)") long rangeStart,
                         @JsonProperty("구간끝(세제곱미터)") long rangeEnd,
                         @JsonProperty("구간금액(원)") long unitPrice,
                         @JsonProperty("단계별 기본요금(원)") Long baseFeePerStageInWon) {
        classInvariant(city, sector, stage, rangeStart, rangeEnd, unitPrice);
        this.sequenceNumber = sequenceNumber;
        this.city = city;
        this.sector = sector;
        this.stage = stage;
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.unitPrice = unitPrice;
        this.baseFeePerStageInWon = Objects.requireNonNullElse(baseFeePerStageInWon, -1L);
    }

    private static void classInvariant(String city, String sector, int stage, long rangeStart, long rangeEnd,
                                  long unitPrice) {
        if(Objects.isNull(city)){
            throw new IllegalArgumentException("city는 null이 될 수 없습니다.");
        }
        if(Objects.isNull(sector)){
            throw new IllegalArgumentException("sector는 null이 될 수 없습니다.");
        }
        if (rangeStart < 0) {
            throw new IllegalArgumentException("rangeStart는 0보다 작을 수 없습니다.");
        }
        if (stage < 0) {
            throw new IllegalArgumentException("stage는 0보다 작을 수 없습니다.");
        }
        if(unitPrice < 0){
            throw new IllegalArgumentException("unitPrice는 0보다 작을 수 없습니다.");
        }
        if (rangeEnd < 0) {
            throw new IllegalArgumentException("rangeEnd는 0보다 작을 수 없습니다.");
        }
        if (rangeEnd < rangeStart) {
            throw new IllegalArgumentException("rangeEnd는 rangeStart보다 작을 수 없습니다.");
        }
    }


    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getStage() {
        return stage;
    }

    public boolean isInRange(long usage) {
        return (rangeStart <= usage && usage <= rangeEnd);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterBillInfo waterBillInfo = (WaterBillInfo) o;
        return sequenceNumber == waterBillInfo.sequenceNumber && stage == waterBillInfo.stage
                && rangeStart == waterBillInfo.rangeStart
                && rangeEnd == waterBillInfo.rangeEnd && Objects.equals(city, waterBillInfo.city)
                && Objects.equals(sector, waterBillInfo.sector) && Objects.equals(unitPrice,
                waterBillInfo.unitPrice) && Objects.equals(baseFeePerStageInWon, waterBillInfo.baseFeePerStageInWon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber, city, sector, stage, rangeStart, rangeEnd, unitPrice, baseFeePerStageInWon);
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public Long getBaseFeePerStageInWon() {
        return baseFeePerStageInWon;
    }


}
