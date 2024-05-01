package com.BookingServices.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FromDTO {
    private String type;
    private String number;
    private String alias;
}
