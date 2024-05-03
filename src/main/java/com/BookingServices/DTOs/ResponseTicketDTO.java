package com.BookingServices.DTOs;

import java.util.Date;
import java.util.List;

public class ResponseTicketDTO {
    private Integer ticketId;
    private Integer hotelId;
    private String fileUrl;
    private String hotelName;
    private String hotelCity;
    private String userName;
    private String userEmail;
    private String userPhone;
    private Date checkInDate;
    private Date checkOutDate;
    private List<Object> productList;
    private String paymentMethod = "Credit Card";
    private Long totalPrice;
    private Integer ownerId;

    public ResponseTicketDTO() {
    }

    public ResponseTicketDTO(Integer ticketId, Integer hotelId, String fileUrl, String hotelName, String hotelCity, String userName, String userEmail, String userPhone, Date checkInDate, Date checkOutDate, Long totalPrice, Integer ownerId) {
        this.ticketId = ticketId;
        this.hotelId = hotelId;
        this.fileUrl = fileUrl;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.ownerId = ownerId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<Object> getProductList() {
        return productList;
    }

    public void setProductList(List<Object> productList) {
        this.productList = productList;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "ResponseTicketDTO{" +
                "ticketId=" + ticketId +
                ", hotelId=" + hotelId +
                ", fileUrl='" + fileUrl + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelCity='" + hotelCity + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", productList=" + productList +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalPrice=" + totalPrice +
                ", ownerId=" + ownerId +
                '}';
    }
}
