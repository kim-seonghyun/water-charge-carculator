package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import com.nhnacademy.edu.springframework.project.service.WaterBillReport;
import com.nhnacademy.edu.springframework.project.service.WaterBillService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.annotation.PropertySource;


public class BootStrap {

    public static void main(String[] args) {
        final String CSV_FILE_PATH = "data/Tariff_20220331.csv";

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class)){
            WaterBillChartRepository waterBillChartRepository = context.getBean(WaterBillChartRepository.class);
            WaterBillReport waterBillReport = context.getBean(WaterBillReport.class);
            waterBillChartRepository.load(CSV_FILE_PATH);

            Scanner scanner = new Scanner(System.in);
            long waterUsage = scanner.nextLong();

            WaterBillService waterBillService = context.getBean(WaterBillService.class);
            List<WaterBill> waterBills = waterBillService.calculateWaterBill(waterUsage);
            System.out.println(waterBillReport.generateReport(waterBills));
        }

    }
}
