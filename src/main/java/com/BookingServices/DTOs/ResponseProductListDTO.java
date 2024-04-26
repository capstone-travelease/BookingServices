package com.BookingServices.DTOs;

import java.math.BigDecimal;

public class ResponseProductListDTO {
    private String roomName;
    private Integer maxGuest;
    private Integer numberRoom;
    private BigDecimal roomPrice;

    public ResponseProductListDTO() {
    }

    public ResponseProductListDTO(String roomName, Integer maxGuest, Integer numberRoom, BigDecimal roomPrice) {
        this.roomName = roomName;
        this.maxGuest = maxGuest;
        this.numberRoom = numberRoom;
        this.roomPrice = roomPrice;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(Integer maxGuest) {
        this.maxGuest = maxGuest;
    }

    public Integer getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(Integer numberRoom) {
        this.numberRoom = numberRoom;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Override
    public String toString() {
        return "ResponseProductListDTO{" +
                "roomName='" + roomName + '\'' +
                ", maxGuest=" + maxGuest +
                ", numberRoom=" + numberRoom +
                ", roomPrice=" + roomPrice +
                '}';
    }
}
