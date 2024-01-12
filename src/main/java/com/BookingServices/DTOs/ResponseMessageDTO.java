package com.BookingServices.DTOs;

public class ResponseMessageDTO {
    private Integer code;
    private String message;

    public ResponseMessageDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseMessageDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
