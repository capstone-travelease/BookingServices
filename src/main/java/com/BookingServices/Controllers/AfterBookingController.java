package com.BookingServices.Controllers;

import com.BookingServices.DTOs.ResponingStatusDTO;
import com.BookingServices.DTOs.TicketRequestDTO;
import com.BookingServices.Services.AfterBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "booking/")
public class AfterBookingController {
    private AfterBookingService afterBookingService;

    @Autowired
    public AfterBookingController(AfterBookingService afterBookingService) {
        this.afterBookingService = afterBookingService;
    }

    @GetMapping("get-orders")
    public ResponingStatusDTO getBooking(@RequestParam(value = "statusName", required = true) String statusName){
        List<Object> data = afterBookingService.getBooking(statusName);

        return new ResponingStatusDTO(
                200,
                data,
                "Successful"
        );
    }

    @GetMapping("getTicket")
    public ResponingStatusDTO getTicket(@RequestBody TicketRequestDTO ticketRequestDTO){
        Object data = afterBookingService.getTicket(ticketRequestDTO.getUserId(), ticketRequestDTO.getBookingId());

        return new ResponingStatusDTO(
                200,
                data,
                "Successful"
        );
    }
}
