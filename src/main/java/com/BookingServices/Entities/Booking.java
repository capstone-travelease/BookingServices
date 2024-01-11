package com.BookingServices.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
public class Booking {
    @Id
    private Integer bookingId;
    private Integer userId;
    private Integer hotelId;
    private Integer bookingStatusId;
    private Date chekinDate;
    private Date checkoutDate;
    private String taxes;
    private String note;
    private Long totalPrice;
    private Integer accountId;

    public Booking(Integer bookingStatusId, Date chekinDate, Date checkoutDate, String taxes, String note, Long totalPrice, Integer accountId) {
        this.bookingStatusId = bookingStatusId;
        this.chekinDate = chekinDate;
        this.checkoutDate = checkoutDate;
        this.taxes = taxes;
        this.note = note;
        this.totalPrice = totalPrice;
        this.accountId = accountId;
    }

    public Booking(Integer bookingId, Integer userId, Integer hotelId, Integer bookingStatusId, Date chekinDate, Date checkoutDate, String taxes, String note, Long totalPrice, Integer accountId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.bookingStatusId = bookingStatusId;
        this.chekinDate = chekinDate;
        this.checkoutDate = checkoutDate;
        this.taxes = taxes;
        this.note = note;
        this.totalPrice = totalPrice;
        this.accountId = accountId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getbookingStatusId() {
        return bookingStatusId;
    }

    public void setbookingStatusId(Integer bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public Date getchekinDate() {
        return chekinDate;
    }

    public void setchekinDate(Date chekinDate) {
        this.chekinDate = chekinDate;
    }

    public Date getcheckoutDate() {
        return checkoutDate;
    }

    public void setcheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
