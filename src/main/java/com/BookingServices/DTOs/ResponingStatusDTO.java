package com.BookingServices.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponingStatusDTO {
    private Integer code;
    private Object data;
    private String message;

}
