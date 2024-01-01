package com.BookingServices.Services;


import com.BookingServices.DTOs.AddNewBankDTO;
import com.BookingServices.Repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    public Map<String,Object> getList(Integer userId){
        boolean isCheckExist = userRepository.existsById(userId);
        if(!isCheckExist){
            return null;
        }
        Map<String,Object> list = new HashMap<>();
        list.put("userId",userId);
        list.put("list",userRepository.listPaymentMethod(userId));
        return list;
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

//    public boolean sendOTP(Integer userId){
//        Integer userData = userRepository.checkUser(userId);
//        if(userData == null){
//            return false;
//        }
//        try {
//            Twilio.init(
//                    "ACbc5c2f92ec9a93660d8a2e398e4033fb",
//                    "8de57fd9e1c50c9cc90c73d2a2e3943b"
//            );
//            PhoneNumber to = new PhoneNumber("+84906385132");
//            PhoneNumber from = new PhoneNumber("+15179170926");
//            String message = "Hello";
//            MessageCreator creator = Message.creator(to,from,message);
//            creator.create();
//            return true;
//        }catch (Exception err){
//            System.out.println(err);
//            return false;
//        }
//    }
}
