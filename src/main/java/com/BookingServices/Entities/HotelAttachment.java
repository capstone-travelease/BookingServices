package com.BookingServices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class HotelAttachment {
    @Id
    private Integer hotel_id;
    private Integer attachment_id;

    public HotelAttachment() {
    }

    public HotelAttachment(Integer hotel_id, Integer attachment_id) {
        this.hotel_id = hotel_id;
        this.attachment_id = attachment_id;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Integer getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(Integer attachment_id) {
        this.attachment_id = attachment_id;
    }
}
