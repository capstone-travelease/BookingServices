package com.BookingServices.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponingStatusArrayDTO {
    private Integer code;
    private List<Map<String, Object>> data;
    private String message;
}
