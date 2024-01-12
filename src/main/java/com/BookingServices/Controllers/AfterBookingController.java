package com.BookingServices.Controllers;

import com.BookingServices.DTOs.AfterBookingRequestDTO;
import com.BookingServices.DTOs.ResponingStatusDTO;
import com.BookingServices.DTOs.ResponseMessageDTO;
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
    public ResponingStatusDTO getBooking(@RequestBody AfterBookingRequestDTO afterBookingRequestDTO){
        List<Object> data = afterBookingService.getBooking(afterBookingRequestDTO.getStatusName(), afterBookingRequestDTO.getUserId());

        return new ResponingStatusDTO(
                200,
                data,
                "Successful"
        );
    }

    @GetMapping("getTicket")
    public ResponingStatusDTO getTicket(@RequestParam(value = "bookingId", required = true) Integer bookingId){
        Object data = afterBookingService.getTicket(bookingId);

        return new ResponingStatusDTO(
                200,
                data,
                "Successful"
        );
    }

    @PutMapping("cancelBooking")
    public ResponseMessageDTO cancelBooking(@RequestParam(value = "bookingId", required = true) Integer bookingId){
        Integer status = afterBookingService.cancelBooking(bookingId);
        if (status == 1){
            return new ResponseMessageDTO(
                    200,
                    "Update Successful"
            );
        }
        else{
            return new ResponseMessageDTO(
                    400,
                    "Not found id : " + bookingId
            );
        }
    }
}
