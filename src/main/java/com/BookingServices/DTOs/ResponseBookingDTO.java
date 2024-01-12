package com.BookingServices.DTOs;

public class ResponseBookingDTO {
    private Integer bookingId;
    private Integer hotelId;
    private String hotelName;
    private String hotelCity;
    private String hotelAddress;
    private String bookingStatus;
    private String fileUrl;
    private Double ratePoint;

    public ResponseBookingDTO() {
    }

    public ResponseBookingDTO(Integer bookingId, Integer hotelId, String hotelName, String hotelCity, String hotelAddress, String bookingStatus, String fileUrl, Double ratePoint) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.hotelAddress = hotelAddress;
        this.bookingStatus = bookingStatus;
        this.fileUrl = fileUrl;
        this.ratePoint = ratePoint;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Double getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(Double ratePoint) {
        this.ratePoint = ratePoint;
    }

    @Override
    public String toString() {
        return "ResponseBookingDTO{" +
                "bookingId=" + bookingId +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelCity='" + hotelCity + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", ratePoint=" + ratePoint +
                '}';
    }
}
