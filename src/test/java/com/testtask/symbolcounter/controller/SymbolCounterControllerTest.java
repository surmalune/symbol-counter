package com.testtask.symbolcounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.symbolcounter.advice.SymbolCounterExceptionHandler;
import com.testtask.symbolcounter.dto.SymbolCounterResponse;
import com.testtask.symbolcounter.service.SymbolCounterService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SymbolCounterControllerTest {

    private MockMvc mvc;

    @Mock
    private SymbolCounterService symbolCounterService;

    @InjectMocks
    private SymbolCounterController symbolCounterController;

    private JacksonTester<SymbolCounterResponse> jsonResponse;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(symbolCounterController)
                             .setControllerAdvice(new SymbolCounterExceptionHandler())
                             .build();
    }

    @Test
    void getSymbolCount_validInput_returnsOk() throws Exception {
        String text = "hello";
        SymbolCounterResponse response = new SymbolCounterResponse(Map.of(
                'l', 2,
                'o', 1,
                'h', 1,
                'e', 1
        ));
        when(symbolCounterService.getSymbolCount(text)).thenReturn(response);

        MockHttpServletResponse result = mvc.perform(MockMvcRequestBuilders.get("/count")
                                                                 .param("text", text))
                                  .andExpect(status().isOk())
                                  .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatus());
        Assertions.assertEquals(jsonResponse.write(response).getJson(), result.getContentAsString());
    }

    @Test
    void getSymbolCount_nonLatinCharacters_throwsConstraintViolationException() throws Exception {
        String text = "абвгд";
        when(symbolCounterService.getSymbolCount(anyString())).thenThrow(new ConstraintViolationException(null));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/count")
                                                                 .param("text", text))
                                  .andExpect(status().isBadRequest())
                                  .andReturn();

        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void getSymbolCount_longString_throwsConstraintViolationException() throws Exception {
        String text = "a".repeat(151);
        when(symbolCounterService.getSymbolCount(anyString())).thenThrow(new ConstraintViolationException(null));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/count")
                                                                 .param("text", text))
                                  .andExpect(status().isBadRequest())
                                  .andReturn();

        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}