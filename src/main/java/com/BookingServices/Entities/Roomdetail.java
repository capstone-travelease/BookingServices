package com.BookingServices.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Roomdetail {
    @Id
    private Integer room_detail_id;
    @Column(name = "room_id")
    private Integer room_id;
    @Column(name = "room_description")
    private String room_description;
    @Column(name = "room_size")
    private String room_size;
    @Column(name =  "room_bed_quantity")
    private Integer room_bed_quantity;
    @Column(name = "room_capacity")
    private Integer room_capacity;

    public Roomdetail() {
    }

    public Roomdetail(Integer room_detail_id, Integer room_id, String room_description, String room_size, Integer room_bed_quantity, Integer room_capacity) {
        this.room_detail_id = room_detail_id;
        this.room_id = room_id;
        this.room_description = room_description;
        this.room_size = room_size;
        this.room_bed_quantity = room_bed_quantity;
        this.room_capacity = room_capacity;
    }

    public Integer getRoom_detail_id() {
        return room_detail_id;
    }

    public void setRoom_detail_id(Integer room_detail_id) {
        this.room_detail_id = room_detail_id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getRoom_description() {
        return room_description;
    }

    public void setRoom_description(String room_description) {
        this.room_description = room_description;
    }

    public String getRoom_size() {
        return room_size;
    }

    public void setRoom_size(String room_size) {
        this.room_size = room_size;
    }

    public Integer getRoom_bed_quantity() {
        return room_bed_quantity;
    }

    public void setRoom_bed_quantity(Integer room_bed_quantity) {
        this.room_bed_quantity = room_bed_quantity;
    }

    public Integer getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(Integer room_capacity) {
        this.room_capacity = room_capacity;
    }
}
