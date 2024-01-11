package com.BookingServices.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table
public class Rooms {
    @Id
    private Integer room_id;
    @Column(name = "room_name")
    private String room_name;
    @Column(name = "room_images")
    private String room_images;
    @Column(name = "room_status")
    private Integer room_status;
    @Column(name = "room_price")
    private BigDecimal room_price;
    @Column(name = "room_type_id")
    private Integer room_type_id;
    @Column(name = "hotel_id")
    private Integer hotel_id;

    @Column(name = "room_quantity")
    private Integer room_quantity;

    public Rooms() {
    }

    public Rooms(Integer room_id, String room_name, String room_images, Integer room_status, BigDecimal room_price, Integer room_type_id, Integer hotel_id, Integer room_quantity) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_images = room_images;
        this.room_status = room_status;
        this.room_price = room_price;
        this.room_type_id = room_type_id;
        this.hotel_id = hotel_id;
        this.room_quantity = room_quantity;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_images() {
        return room_images;
    }

    public void setRoom_images(String room_images) {
        this.room_images = room_images;
    }

    public Integer getRoom_status() {
        return room_status;
    }

    public void setRoom_status(Integer room_status) {
        this.room_status = room_status;
    }

    public BigDecimal getRoom_price() {
        return room_price;
    }

    public void setRoom_price(BigDecimal room_price) {
        this.room_price = room_price;
    }

    public Integer getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(Integer room_type_id) {
        this.room_type_id = room_type_id;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Integer getRoom_quantity() {
        return room_quantity;
    }

    public void setRoom_quantity(Integer room_quantity) {
        this.room_quantity = room_quantity;
    }
}
