package com.testtask.symbolcounter.service;

import com.testtask.symbolcounter.dto.SymbolCounterResponse;
import com.testtask.symbolcounter.service.impl.SymbolCounterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SymbolCounterServiceTest {

    @InjectMocks
    private SymbolCounterServiceImpl symbolCounterService;

    @Test
    public void getSymbolCount_ReturnsExpectedResult_WhenTextIsValid() {
        String text = "hello";
        SymbolCounterResponse actualResponse = symbolCounterService.getSymbolCount(text);

        SymbolCounterResponse expectedResponse = new SymbolCounterResponse(Map.of(
                'l', 2,
                'o', 1,
                'h', 1,
                'e', 1
        ));

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getSymbolCount_ReturnsEmptyMap_WhenTextIsEmpty() {
        String text = "";
        SymbolCounterResponse expectedResponse = new SymbolCounterResponse(Collections.emptyMap());

        SymbolCounterResponse actualResponse = symbolCounterService.getSymbolCount(text);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getSymbolCount_ReturnsEmptyMap_WhenTextIsNull() {
        String text = null;
        SymbolCounterResponse expectedResponse = new SymbolCounterResponse(Collections.emptyMap());

        SymbolCounterResponse actualResponse = symbolCounterService.getSymbolCount(text);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
