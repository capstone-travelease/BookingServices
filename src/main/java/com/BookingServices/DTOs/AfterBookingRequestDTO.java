package com.BookingServices.DTOs;

public class AfterBookingRequestDTO
{
    private String statusName;
    private Integer userId;

    public AfterBookingRequestDTO(String statusName, Integer userId) {
        this.statusName = statusName;
        this.userId = userId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
