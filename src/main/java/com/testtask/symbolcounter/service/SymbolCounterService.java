package com.testtask.symbolcounter.service;

import com.testtask.symbolcounter.dto.SymbolCounterResponse;

public interface SymbolCounterService {

    SymbolCounterResponse getSymbolCount(String text);
}
