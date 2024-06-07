package com.BookingServices.Services;

import com.BookingServices.DTOs.ResponseBookingDTO;
import com.BookingServices.DTOs.ResponseTicketDTO;
import com.BookingServices.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AfterBookingService {
    private BookingRepository bookingRepository;

    @Autowired
    public AfterBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<ResponseBookingDTO> getBooking(String statusName, Integer userId){
        List<ResponseBookingDTO> dataBooking = bookingRepository.getBookingList(statusName, userId);

        Map<Integer, List<ResponseBookingDTO>> groupedAttachment = dataBooking.stream()
                .collect(Collectors.groupingBy(ResponseBookingDTO::getBookingId));

        List<ResponseBookingDTO> transformedBooking = groupedAttachment.values().stream()
                .map(hotelsInGroup -> hotelsInGroup.get(0))
                .collect(Collectors.toList());

        return transformedBooking;
    }

    public Object getTicket(Integer bookingId){
        List<ResponseTicketDTO> dataTicket = bookingRepository.getTicket(bookingId);
        List<Object> data = bookingRepository.getProductList(bookingId);

        for(ResponseTicketDTO data1 : dataTicket){
            data1.setProductList(data);
        }

        Object result = (Object) dataTicket.get(0);
        System.out.println(result);
        return result;
    }

    public Integer cancelBooking(Integer bookingId){
        List<ResponseTicketDTO> dataTicket = bookingRepository.getTicket(bookingId);

        if(dataTicket.get(0) == null){
            return 2;
        }

        bookingRepository.updateBooking(3, bookingId);
        return 1;
    }
}
