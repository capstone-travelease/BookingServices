package com.BookingServices.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductListDTO {
    private Integer roomId;
    private Integer roomQuantity;
    private Integer roomPrice;
}
