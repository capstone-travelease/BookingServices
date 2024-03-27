package com.BookingServices.Services;


import com.BookingServices.DTOs.AddNewBankDTO;
import com.BookingServices.DTOs.BookingRequestDTO;
import com.BookingServices.DTOs.ProductListDTO;
import com.BookingServices.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    public Object getList(Integer userId){
        Integer isCheckExist = userRepository.checkUser(userId);
        if(isCheckExist == null){
            return null;
        }
        Map<String,Object> list = new HashMap<>();
        list.put("userId",userId);
        list.put("list",userRepository.listPaymentMethod(userId));
        return list.get("list");
    }
    public List<Map<String,Object>> getAllBanks(Integer type){
        List<Map<String,Object>> isCheckNull = userRepository.listingBank(type);
        if(isCheckNull.isEmpty()){
            return Collections.emptyList();
        }
        return userRepository.listingBank(type);
    }

    public Integer addBanks(Integer userId, AddNewBankDTO request){
         Integer user = userRepository.checkUser(userId);
         Integer bank = userRepository.checkBank(request.getBankId());
         List<String> accountNumber = userRepository.checkAccount(userId,request.getBankId());
         if(user == null || bank == null){
             return 0;
         }
        for (String i : accountNumber) {
            if(i.equals(request.getAccountNumber())){
                return 2;
            }
        }
         userRepository.insertBank(userId,request.getBankId(),request.getAccountName(),request.getAccountNumber());
         return 1;
    }


    @RabbitListener(queues = "queue_book")
    public Integer addTicket(BookingRequestDTO data) {
        try {
            Integer userId = userRepository.checkUser(data.getUserId());
            Integer hotelId= userRepository.checkHotel(data.getHotelId());
            boolean isCheckDate = validateDate(data.getCheckinDate(),data.getCheckoutDate());
            if(userId == null || hotelId == null){
               return 1;
            }
            if(!isCheckDate){
               return 2;
            }
            Date dateNow = new Date();
            Integer bookingId = randomBookingId();
            userRepository.insertTicket(bookingId,data.getUserId(),data.getHotelId(),1,data.getCheckinDate(), data.getCheckoutDate(), data.getTaxes(), data.getCoupon(), data.getNote(), data.getTotalPrice(),data.getAccountId(),dateNow);
            boolean isCheckAddProduct = addProductList(data.getProductList(),bookingId);
            if(!isCheckAddProduct){
                return 3;
            }
            return 4;
        }catch (Exception exception){
            System.out.println(exception);
            return 3;
        }
    }
    private boolean addProductList(List<ProductListDTO> productList, Integer bookingId){
        try{
            for (ProductListDTO i: productList
                 ) {
               if(userRepository.checkRooms(i.getRoomId()) == null){
                   return false;
               }
               userRepository.insertProductList(i.getRoomId(), bookingId,i.getRoomQuantity());
            }
            return true;
        }catch (Exception exception){
            System.out.println(exception);
            return false;
        }
    }
    private boolean validateDate(Date checkIn, Date checkOut){
        Date dateNow = new Date();
        if(checkIn.equals(dateNow) || checkIn.after(dateNow) || checkOut.after(dateNow)){
          if (checkIn.before(checkOut))
              return true;
       }
        return false;
    }

    private Integer randomBookingId(){
        Integer ranNum = null;
        for (int i=0; i<5; i++) {
            Random rand = new Random();
            ranNum = rand.nextInt(10000) * 2;
        }
        return ranNum;
    }
}
