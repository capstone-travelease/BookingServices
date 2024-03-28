package com.BookingServices.Services;


import com.BookingServices.DTOs.AddNewBankDTO;
import com.BookingServices.DTOs.BookingRequestDTO;
import com.BookingServices.DTOs.ProductListDTO;
import com.BookingServices.Repositories.UserRepository;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;
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
            boolean isCheckRoomNull =  checkProductList(data.getProductList());
            if(userId == null || hotelId == null){
               return 1;
            }
            if(!isCheckDate || !isCheckRoomNull){
               return 2;
            }
            Date dateNow = new Date();
            Integer bookingId = randomBookingId();
            userRepository.insertTicket(bookingId,data.getUserId(),data.getHotelId(),1,data.getCheckinDate(), data.getCheckoutDate(), data.getTaxes(), data.getCoupon(), data.getNote(), data.getTotalPrice(),data.getAccountId(),dateNow);
            addProductList(data.getProductList(),bookingId);
//            List<Object> ticketInformation = userRepository.getTicketInformation(170);
//            customizeTicket(ticketInformation);

//            sendMail();
            return 4;
        }catch (Exception exception){
            System.out.println(exception);
            return 3;
        }
    }



//    public List<Object> customizeTicket(List<Object> data){
//        List<Object> ticketInformation = userRepository.getTicketInformation(170);
//        return ticketInformation;
//    }
//
//    private boolean sendMail(BookingRequestDTO data){
//        MimeMessage message = sender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
//            Template t = config.getTemplate("bookingtemplates.ftl");
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,data);
//            helper.setTo("ngoctien.forwork@gmail.com");
//            helper.setText(html, true);
//            helper.setSubject("Pay Success !!");
//            helper.setFrom("capstonevlu1204@gmail.com");
//            sender.send(message);
//            return true;
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (TemplateNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        } catch (MalformedTemplateNameException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (TemplateException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private boolean checkProductList(List<ProductListDTO> productList){
        for (ProductListDTO i: productList
        ) {
            if(userRepository.checkRooms(i.getRoomId()) == null){
                return false;
            }
        }
        return true;
    }
    private boolean addProductList(List<ProductListDTO> productList, Integer bookingId){
        try{
            for (ProductListDTO i: productList
                 ) {
               userRepository.insertProductList(i.getRoomId(), bookingId,i.getRoomQuantity(),i.getRoomPrice());
            }
            return true;
        }catch (Exception exception){
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
