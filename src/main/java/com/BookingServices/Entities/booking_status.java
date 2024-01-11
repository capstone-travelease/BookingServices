package com.BookingServices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class booking_status {
    @Id
    private Integer bookingStatusId;
    private String bookingStatusName;

    public booking_status() {
    }

    public booking_status(String bookingStatusName) {
        this.bookingStatusName = bookingStatusName;
    }

    public booking_status(Integer bookingStatusId, String bookingStatusName) {
        this.bookingStatusId = bookingStatusId;
        this.bookingStatusName = bookingStatusName;
    }

    public Integer getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Integer bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public String getBookingStatusName() {
        return bookingStatusName;
    }

    public void setBookingStatusName(String bookingStatusName) {
        this.bookingStatusName = bookingStatusName;
    }
}
