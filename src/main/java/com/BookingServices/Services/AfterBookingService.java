package com.BookingServices.Services;

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

    public Object getTicket(Integer bookingId){
        ResponseTicketDTO dataTicket = bookingRepository.getTicket(bookingId);
        List<Object> data = bookingRepository.getProductList(bookingId);
        dataTicket.setProductList(data);
        Object result = (Object) dataTicket;
        return result;
    }

    public Integer cancelBooking(Integer bookingId){
        ResponseTicketDTO dataTicket = bookingRepository.getTicket(bookingId);

        if(dataTicket == null){
            return 2;
        }

        bookingRepository.updateBooking(3, bookingId);
        return 1;
    }
}
