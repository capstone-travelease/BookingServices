package com.BookingServices.Services;

import com.BookingServices.DTOs.ResponseBookingDTO;
import com.BookingServices.DTOs.ResponseTicketDTO;
import com.BookingServices.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfterBookingService {
    private BookingRepository bookingRepository;

    @Autowired
    public AfterBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Object> getBooking(String statusName, Integer userId){
        List<Object> dataBooking = bookingRepository.getBookingList(statusName, userId);

        return dataBooking;
    }

    public Object getTicket(Integer userId, Integer bookingId){
        ResponseTicketDTO dataTicket = bookingRepository.getTicket(userId, bookingId);
        List<Object> data = bookingRepository.getProductList(bookingId);

        dataTicket.setProductList(data);

        Object result = (Object) dataTicket;

        return result;
    }
}
