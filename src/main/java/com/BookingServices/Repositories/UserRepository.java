package com.BookingServices.Repositories;

import com.BookingServices.Entities.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface UserRepository extends JpaRepository<Accounts,Integer> {
    @Query(value = "SELECT ac.id_account as \\\"accountId\\\",ac.bank_id as \\\"bankId\\\", b.namebank as \\\"nameBank\\\", b.imagebank as \\\"imageBank\\\", b.typeBank as \\\"typeBank\\\", ac.accountnumber as \\\"accountNumber\\\",ac.accountName as \\\"accountName\\\" FROM accounts ac\n" +
            "INNER JOIN banks b ON b.bank_id = ac.bank_id\n" +
            "WHERE ac.user_id = ?",nativeQuery = true)
    List<Map<String, Object>> listPaymentMethod(Integer userId);

    @Query(value = "SELECT bank_id as \\\"bankId\\\", namebank as \\\"nameBank\\\", imagebank as \\\"imageBank\\\", typebank as \\\"bankType\\\"\n" +
            "\tFROM public.banks b where b.typebank = ?1",nativeQuery = true)
    List<Map<String,Object>> listingBank(Integer typebank);

    @Query(value = "Select user_id from users u where u.user_id = ?1",nativeQuery = true)
    Integer checkUser(Integer userId);
    @Query(value = "Select accountNumber from accounts a where a.user_id = ?1 and a.bank_id = ?2",nativeQuery = true)
    List<String> checkAccount(Integer userId,Integer bankId);

    @Query(value = "Select hotel_id from hotels h where h.hotel_id = ?1",nativeQuery = true)
    Integer checkHotel(Integer hotelId);
    @Query(value = "Select bank_id from banks b where b.bank_id = ?1",nativeQuery = true)
    Integer checkBank(Integer bankId);

    @Query(value = "Select room_id from rooms r where r.room_id = ?1",nativeQuery = true)
    Integer checkRooms(Integer roomId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO public.booking(\n" +
            "\tbooking_id, user_id,  hotel_id, booking_status_id, checkin_date, checkout_date, taxes, coupon, note, total_price, id_account, booking_date)\n" +
            "\tVALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12);",nativeQuery = true)
    void insertTicket(Integer bookingId,Integer userId,Integer hotelId,Integer status,Date checkin, Date checkout, String taxes, String coupon, String note, Integer total,Integer accountId,Date dateNow);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO public.product_list(\n" +
            "\troom_id, booking_id, room_quantity)\n" +
            "\tVALUES (?1, ?2, ?3);",nativeQuery = true)
    void insertProductList(Integer roomId, Integer idBooking, Integer roomQuality);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO public.accounts(\n" +
            "\tuser_id, bank_id, accountname, accountnumber)\n" +
            "\tVALUES (?1, ?2, ?3, ?4);",nativeQuery = true)
    void insertBank(Integer userId,Integer bankId, String accountName, String accountNumber);

}
