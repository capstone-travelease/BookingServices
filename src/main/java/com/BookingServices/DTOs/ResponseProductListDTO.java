package com.BookingServices.DTOs;

public class ResponseProductListDTO {
    private String roomName;
    private Integer maxGuest;
    private Integer numberRoom;

    public ResponseProductListDTO() {
    }

    public ResponseProductListDTO(String roomName, Integer maxGuest, Integer numberRoom) {
        this.roomName = roomName;
        this.maxGuest = maxGuest;
        this.numberRoom = numberRoom;
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

    @Override
    public String toString() {
        return "ResponseProductListDTO{" +
                "roomName='" + roomName + '\'' +
                ", maxGuest=" + maxGuest +
                ", numberRoom=" + numberRoom +
                '}';
    }
}
