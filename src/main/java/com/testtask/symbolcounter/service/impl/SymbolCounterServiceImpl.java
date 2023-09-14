package com.testtask.symbolcounter.service.impl;

import com.testtask.symbolcounter.dto.SymbolCounterResponse;
import com.testtask.symbolcounter.service.SymbolCounterService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SymbolCounterServiceImpl implements SymbolCounterService {

    @Override
    public SymbolCounterResponse getSymbolCount(String text) {
        if (text == null || text.isEmpty())
            return new SymbolCounterResponse(Collections.emptyMap());

        Map<Character, Integer> symbolMap = new HashMap<>();
        text.chars()
            .filter(Character::isLetter)
            .map(Character::toLowerCase)
            .mapToObj(c -> (char) c)
            .forEach(symbol -> symbolMap.put(symbol, symbolMap.getOrDefault(symbol, 0) + 1));

        List<Map.Entry<Character, Integer>> symbolList = new ArrayList<>(symbolMap.entrySet());
        symbolList.sort(Map.Entry.<Character, Integer>comparingByValue().reversed());

        Map<Character, Integer> sortedSymbolMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : symbolList) {
            sortedSymbolMap.put(entry.getKey(), entry.getValue());
        }

        return new SymbolCounterResponse(sortedSymbolMap);
    }
}
