package com.BookingServices.DTOs;

public class TicketRequestDTO {
    private Integer userId;
    private Integer bookingId;

    public TicketRequestDTO() {
    }

    public TicketRequestDTO(Integer userId, Integer bookingId) {
        this.userId = userId;
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
