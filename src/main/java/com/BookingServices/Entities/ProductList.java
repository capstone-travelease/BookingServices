package com.BookingServices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class ProductList {
    @Id
    private Integer id;
    private Integer roomId;
    private Integer bookingId;
    private Integer roomQuantity;

    public ProductList(Integer roomId, Integer bookingId,  Integer roomQuantity) {
        this.roomId = roomId;
        this.bookingId = bookingId;
        this.roomQuantity = roomQuantity;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(Integer roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
}
