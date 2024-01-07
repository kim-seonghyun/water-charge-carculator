package com.nhnacademy.edu.springframework.project.aspect;

import com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckLoadedAspect {
    private final WaterBillChartRepository waterBillChartRepository;

    public CheckLoadedAspect(WaterBillChartRepository waterBillChartRepository){
        this.waterBillChartRepository = waterBillChartRepository;
    }

    @Before("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..)) && !execution(* com.nhnacademy.edu.springframework.project.repository.WaterBillChartRepository.load())")
    public void checkLoaded() {
        waterBillChartRepository.isLoaded();
    }
}
