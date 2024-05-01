package com.BookingServices.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity(name = "OTP")
@Table(name = "otp")
public class OTP {

    @Id
    private  String otp_code;
    private LocalDateTime expired;
    private Integer user_id;

    public OTP(String otp_code, LocalDateTime expired, Integer user_id) {
        this.otp_code = otp_code;
        this.expired = expired;
        this.user_id = user_id;
    }

    public OTP() {
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "OTP{" +
                "otp_code='" + otp_code + '\'' +
                ", expired=" + expired +
                ", user_id=" + user_id +
                '}';
    }
}
