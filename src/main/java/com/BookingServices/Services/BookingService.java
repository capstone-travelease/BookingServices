package com.BookingServices.Services;


import com.BookingServices.DTOs.*;
import com.BookingServices.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import freemarker.core.ParseException;
import freemarker.template.*;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;

    @Autowired
    private JWTService jwtService;

    private final String Phone_From_Number = "842871053287";

    private final String url_Stringge = "https://api.stringee.com/v1/call2/callout";


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
            Map<String,Object> ticketInformation =  userRepository.getTicketInformation(bookingId);
            Map<String,Object> ticket = new HashMap<>(ticketInformation);
            List<Map<String,Object>> productList = userRepository.listProduct(bookingId);
            ticket.put("list",productList);
            sendMail(ticket);
            return 4;
        }catch (Exception exception){
            System.out.println(exception);
            return 3;
        }
    }


    public Integer getOTP(Integer userId)  {
        try {
            HttpHeaders headers = setHeader();
            Map<String,Object> user = userRepository.getUserOTP(userId);
            if(user.isEmpty()){
                return 404;
            }
            String otp = generateOTP();
            String otpFomat = "";
            for (Integer i = 0; i < otp.length(); i++){
                otpFomat += otp.charAt(i) + ",";
            }

            userRepository.addOTPUser(otp,new Date(System.currentTimeMillis() + 1000 * 60 * 10), userId);
            String body = setBody((String) user.get("phoneNumber"), (String) user.get("fullName"),otpFomat);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(body, headers );

            ResponseEntity<String> response = restTemplate.postForEntity(url_Stringge, request, String.class);

            HttpStatusCode statusCode = response.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                String responseBody = response.getBody();
                System.out.println("Response Body: " + responseBody);

                return 200;
            } else {
                System.out.println("Failed to make call. Error response: " + response.getBody());
                return 500;
            }
        } catch (Exception exception){
            System.out.println(exception);
            return 500;
        }

    }
    @Scheduled(cron = "15 * * * * ?")
    private void removeOTPExpired() throws java.text.ParseException {
        List<Map<String, Object>> listOTP = userRepository.getAllOTP();
        Date dateNow = new Date();
        for (Map<String,Object> i: listOTP) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSX");
            Date date = dateFormat.parse((String) i.get("expired"));
            boolean isCheckExpired = date.before(new Date());
            if (isCheckExpired){
                 userRepository.removeOTP((String) i.get("otpCode"));
            }
        }
    }
    public Integer checkOTP(Integer userId, String OTP){
        try {
            String expired = userRepository.isExpired(userId,OTP);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSX");
            Date date = dateFormat.parse(expired);
            boolean isCheckExpired = date.before(new Date());
            if(!isCheckExpired){
                return 200;
            }
            return 406;
        }catch (Exception exception){
            System.err.println(exception);
            return 500;
        }
    }
   private String setBody(String phoneNumber, String fullname,String otp)  {
       try {
           JsonMapper mapper = new JsonMapper();
           Map<String,FromDTO> dataFrom = new HashMap<>();
           FromDTO from = new FromDTO("external",Phone_From_Number,"STRINGEE_NUMBER");
           // TO
           List<FromDTO> to = new ArrayList<>();
           to.add(new FromDTO("external","84" + phoneNumber,"TO_NUMBER"));

           // asnwer
           Map<String,String> answerUrl = new HashMap<>();
           answerUrl.put("answer_url","https://example.com/answerurl");

           // action
           Map<String,Object> actionData = new HashMap<>();
           actionData.put("action","talk");
           actionData.put("text","Xin chào" + fullname + " mã xác thực của anh/chị là " + otp + "Xin nhắc lại mã xác thực là " + otp + "Thời hạn của mã OTP là 10 phút");
           actionData.put("speed",-2);
           List<Object> actions = new ArrayList<>();
           actions.add(actionData);

           BodyDTO body = new BodyDTO(from,to,answerUrl,actions);

           return mapper.writeValueAsString(body);
       }catch (Exception e){
               System.out.println(e);
               return null;
       }
   }

    private String generateOTP(){
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 4; i++) {
            Integer code = random.nextInt(10);
            result  +=  code.toString();
        }
        return result;
    }


    private HttpHeaders setHeader(){
        String token = jwtService.generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.set("X-STRINGEE-AUTH", token);
        return headers;
    }



    private boolean sendMail(Map<String,Object> data){
        MimeMessage message = sender.createMimeMessage();
        try {
            String email = (String) data.get("email");
            MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
            Template t = config.getTemplate("bookingtemplates.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,data);
            helper.setTo(email);
            helper.setText(html, true);
            helper.setSubject("Pay Success !!");
            helper.setFrom("capstonevlu1204@gmail.com");
            sender.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
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
