package com.testtask.symbolcounter.controller;

import com.testtask.symbolcounter.dto.SymbolCounterResponse;
import com.testtask.symbolcounter.service.SymbolCounterService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
public class SymbolCounterController {

    private final SymbolCounterService symbolCounterService;

    @GetMapping("/count")
    public ResponseEntity<SymbolCounterResponse> getSymbolCount(@RequestParam("text")
                                                                @Length(max = 1000000, message = "String's length should be less than 1000000")
                                                                @Pattern(regexp = "^[a-zA-Z]*$", message = "String contains non-latin characters")
                                                                String text) {
        SymbolCounterResponse response = symbolCounterService.getSymbolCount(text);
        return ResponseEntity.ok().body(response);
    }
}
