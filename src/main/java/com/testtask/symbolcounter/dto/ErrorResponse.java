package com.testtask.symbolcounter.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private String message;
    private Date timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = new Date();
    }
}
