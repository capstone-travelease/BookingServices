package com.BookingServices.Controllers;


import com.BookingServices.DTOs.*;
import com.BookingServices.Services.AfterBookingService;
import com.BookingServices.Services.BookingService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private  final BookingService bookingService;
    @Autowired
    private AfterBookingService afterBookingService;

//    @GetMapping("test")
//    public List<Object> test(){
//        return bookingService.customizeTicket(null);
//    }

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
    public ResponseMessageDTO cancelBooking(@RequestParam(value = "bookingId", required = true) Integer bookingId, HttpServletResponse response){
        Integer status = afterBookingService.cancelBooking(bookingId);
        if (status == 1){
            response.setStatus(200);
            return new ResponseMessageDTO(
                    200,
                    "Cancel Ticket with id : " + bookingId+ " Successful"
            );
        }
        else{
            response.setStatus(404);
            return new ResponseMessageDTO(
                    404,
                    "Not found id : " + bookingId
            );
        }
    }
    @GetMapping("/getlistaccount")
    public ResponingStatusDTO getAllAccount(@RequestParam("userid") Integer userId, HttpServletResponse response){
        var Services = bookingService.getList(userId);
        if (Services == null){
            response.setStatus(404);
            return new ResponingStatusDTO(response.getStatus(),null,"NOT_FOUND");
        }
        return new ResponingStatusDTO(response.getStatus(), Services,"OK");
    }

    @GetMapping("/getbank")
    public ResponingStatusArrayDTO getAllBanks(@RequestParam("banktype") Integer banktype, HttpServletResponse response){
        List<Map<String,Object>> banks = bookingService.getAllBanks(banktype);
        if(banks.isEmpty()){
            response.setStatus(404);
            return new ResponingStatusArrayDTO(response.getStatus(),null,"Bank's Type isn't exited");
        }
        return new ResponingStatusArrayDTO(response.getStatus(),banks,"Ok");
    }

    @PostMapping("/addnewbank")
    public ResponingStatusDTO addNewBank(@RequestParam(value = "userid",required = true) Integer userid, HttpServletResponse response,@RequestBody @Valid AddNewBankDTO data){
        Integer Services = bookingService.addBanks(userid,data);
        if(Services == 1){
            return  new ResponingStatusDTO(response.getStatus(),null,"OK");
        } else if (Services == 0) {
            response.setStatus(404);
            return new ResponingStatusDTO(response.getStatus(),null,"NOT FOUND");
        }
        response.setStatus(409);
        return new ResponingStatusDTO(response.getStatus(),null,"The bank account of this bank  is existed");
    }

    @GetMapping("/otp/{id}")
    public Map<String,String> getOTP(@PathVariable("id") Integer userId, HttpServletResponse response){
              Integer httpCode = bookingService.getOTP(userId);
              Map<String,String> returnRespone = new HashMap<>();
              if(httpCode == HttpServletResponse.SC_INTERNAL_SERVER_ERROR){
                  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                  returnRespone.put("message","Internal_Server_ERROR");
                  return returnRespone;
              } else if (httpCode == HttpServletResponse.SC_NOT_FOUND) {
                  response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                  returnRespone.put("message","User isn't existed");
                  return returnRespone;
              }
        returnRespone.put("message","Success");
              return returnRespone;
    }

    @PostMapping("/otp/{id}")
    public Map<String,String> checkOTP(@PathVariable("id") Integer userId,@RequestBody OTPRequestDTO otp, HttpServletResponse response){
       Integer httpCode = bookingService.checkOTP(userId,otp.getOtpCode());
        Map<String,String> returnRespone = new HashMap<>();
       if(httpCode == HttpServletResponse.SC_NOT_ACCEPTABLE){
           response.setStatus(httpCode);
           returnRespone.put("message","OTP is expired");
           return returnRespone;
       } else if (httpCode == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
           returnRespone.put("message","OTP is expired");
           return returnRespone;
       }
        returnRespone.put("message","OK");
       return returnRespone;
    }
    @PostMapping("/order")
    public ResponingStatusDTO addBooking(@RequestBody @Valid BookingRequestDTO data, HttpServletResponse response){
        Integer serviceStatus = bookingService.addTicket(data);
        if(serviceStatus == 1){
            response.setStatus(404);
            return new ResponingStatusDTO(response.getStatus(),null,"NOT FOUND");
        } else if (serviceStatus == 2) {
            response.setStatus(400);
            return new ResponingStatusDTO(response.getStatus(),null,"BAD REQUEST");
        } else if (serviceStatus == 3) {
            response.setStatus(501);
            return new ResponingStatusDTO(response.getStatus(),null,"Not Implemented");
        }
        return new ResponingStatusDTO(response.getStatus(),null,"OK");
    }
}
