package com.BookingServices.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AddNewBankDTO {

    @NotNull
    @NotBlank
    private String accountName;

    @NotNull
    private Integer bankId;

    @NotNull
    @NotBlank
    @Size(min = 16, max = 19)
    private String accountNumber;
}
