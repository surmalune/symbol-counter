package com.testtask.symbolcounter.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class SymbolCounterResponse {

    private Map<Character, Integer> symbols;
}
