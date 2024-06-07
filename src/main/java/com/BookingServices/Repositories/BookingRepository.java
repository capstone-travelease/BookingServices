package com.BookingServices.Repositories;

import com.BookingServices.DTOs.ResponseBookingDTO;
import com.BookingServices.DTOs.ResponseTicketDTO;
import com.BookingServices.Entities.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT new com.BookingServices.DTOs.ResponseBookingDTO(b.bookingId, h.hotel_id, h.hotel_name, h.hotel_city, h.hotel_country, bs.bookingStatusName, atc.file_url, h.star_rating) FROM Booking b\n" +
            "INNER JOIN Hotels h ON h.hotel_id = b.hotelId\n" +
            "INNER JOIN Users u ON u.id = b.userId\n" +
            "INNER JOIN booking_status bs ON bs.bookingStatusId = b.bookingStatusId\n" +
            "INNER JOIN HotelAttachment hatc ON hatc.hotel_id = h.hotel_id\n"+
            "INNER JOIN Attachment atc ON atc.attachment_id = hatc.attachment_id\n"+
            "WHERE bs.bookingStatusName = ?1 AND u.id = ?2")
    List<ResponseBookingDTO> getBookingList(String statusName, Integer userId);

    @Query("SELECT new com.BookingServices.DTOs.ResponseTicketDTO(b.bookingId, h.hotel_id, atc.file_url, h.hotel_name, h.hotel_city, u.fullname, u.email, u.phone, b.checkinDate, b.checkoutDate, b.totalPrice, h.ownerId) FROM Booking b\n" +
            "INNER JOIN Hotels h ON h.hotel_id = b.hotelId\n" +
            "INNER JOIN Users u ON u.id = b.userId\n" +
            "INNER JOIN booking_status bs ON bs.bookingStatusId = b.bookingStatusId\n" +
            "INNER JOIN HotelAttachment hatc ON hatc.hotel_id = h.hotel_id\n" +
            "INNER JOIN Attachment atc ON atc.attachment_id = hatc.attachment_id\n" +
            "WHERE b.bookingId = ?1")
    List<ResponseTicketDTO> getTicket(Integer bookingId);

    @Query("SELECT new com.BookingServices.DTOs.ResponseProductListDTO(r.room_name, rd.room_capacity, pl.roomQuantity) FROM ProductList pl\n" +
            "INNER JOIN Booking b ON b.bookingId = pl.bookingId\n" +
            "INNER JOIN Rooms r ON r.room_id = pl.roomId\n" +
            "INNER JOIN Roomdetail rd ON rd.room_id = r.room_id\n" +
            "WHERE b.bookingId = ?1")
    List<Object> getProductList(Integer bookingId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Booking SET booking_status_id = ?1 WHERE booking_id = ?2", nativeQuery = true)
    void updateBooking(Integer statusId, Integer bookingId);



}
