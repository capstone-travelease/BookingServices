package com.BookingServices.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserOtpDTO {
    private Integer userId;
    private String email;
    private String phoneNumber;
    private String fullName;
}
