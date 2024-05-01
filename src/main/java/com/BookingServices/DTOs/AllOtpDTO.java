package com.BookingServices.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllOtpDTO {

    private String otpCode;
    private String expired;
}
