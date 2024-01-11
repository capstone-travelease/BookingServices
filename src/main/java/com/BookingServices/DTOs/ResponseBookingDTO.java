package com.BookingServices.DTOs;

public class ResponseBookingDTO {
    private Integer hotelId;
    private String hotelName;
    private String hotelAddress;
    private String bookingStatus;
    private String fileUrl;
    private Double ratePoint;

    public ResponseBookingDTO() {
    }

    public ResponseBookingDTO(String hotelName, String hotelAddress, String bookingStatus, String fileUrl, Double ratePoint) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.bookingStatus = bookingStatus;
        this.fileUrl = fileUrl;
        this.ratePoint = ratePoint;
    }

    public ResponseBookingDTO(Integer hotelId, String hotelName, String hotelAddress, String bookingStatus, String fileUrl, Double ratePoint) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.bookingStatus = bookingStatus;
        this.fileUrl = fileUrl;
        this.ratePoint = ratePoint;
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
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", ratePoint=" + ratePoint +
                '}';
    }
}
