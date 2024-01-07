package com.nhnacademy.edu.springframework.project.aspect;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ElapseLoggerTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;


    @Mock
    private Log log;
    @InjectMocks
    private ElapseLogger elapseLogger;
    @Mock
    private Signature signature;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLogTimeElapse() throws Throwable {
        doNothing().when(log).info(anyString());
        when(proceedingJoinPoint.getTarget()).thenReturn(new Object());
        when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("methodName");

        when(proceedingJoinPoint.proceed()).thenReturn(new Object());

        Object result = elapseLogger.logElapse(proceedingJoinPoint);

        assertNotNull(result);
        verify(proceedingJoinPoint, times(1)).proceed();
    }
}