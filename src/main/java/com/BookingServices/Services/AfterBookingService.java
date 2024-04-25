package com.BookingServices.Services;

import com.BookingServices.DTOs.ResponseBookingDTO;
import com.BookingServices.DTOs.ResponseTicketDTO;
import com.BookingServices.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public List<Object> getBooking(String statusName, Integer userId){
        List<ResponseBookingDTO> dataBooking = bookingRepository.getBookingList(statusName, userId);
        List<Object> result = new LinkedList<>();

        Map<Integer, List<ResponseBookingDTO>> groupedAttachment = dataBooking.stream()
                .collect(Collectors.groupingBy(ResponseBookingDTO::getBookingId));

        List<Object> transformedImage = groupedAttachment.values().stream()
                .map(hotelsInGroup -> hotelsInGroup.get(0))
                .collect(Collectors.toList());

//        System.out.println(transformedImage);

        return transformedImage;
    }

    public Object getTicket(Integer bookingId){
        List<ResponseTicketDTO> dataTicket = bookingRepository.getTicket(bookingId);
        List<Object> data = bookingRepository.getProductList(bookingId);

        dataTicket.get(0).setProductList(data);

        Object result = (Object) dataTicket.get(0);

        return result;
    }

    public Integer cancelBooking(Integer bookingId){
        List<ResponseTicketDTO> dataTicket = bookingRepository.getTicket(bookingId);

        if(dataTicket == null){
            return 2;
        }

        bookingRepository.updateBooking(3, bookingId);
        return 1;
    }
}
