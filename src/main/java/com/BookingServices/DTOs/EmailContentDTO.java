package com.BookingServices.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EmailContentDTO {

    private String full_name;
    private String email;
    private Integer booking_id;
    private Date booking_date;
    private String hotel_name;
    private String hotel_address;
    private Date checkin_date;
    private Date checkout_date;
    private String booking_status_name;
    private String note;
    private Integer room_quantity;
    private Integer price;
    private String room_name;
    private String namebank;
    private Integer total_price;
}
