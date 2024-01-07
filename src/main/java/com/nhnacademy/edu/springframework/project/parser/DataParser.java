package com.nhnacademy.edu.springframework.project.parser;

import com.nhnacademy.edu.springframework.project.domain.WaterBillInfo;
import java.util.List;

public interface DataParser {
    /**
     * @param filePath notNull, notEmpty
     * @return notNull
     */
    List<WaterBillInfo> parse(String filePath);
}
