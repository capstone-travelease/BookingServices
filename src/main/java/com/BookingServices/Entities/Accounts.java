package com.BookingServices.Entities;


import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "accountNumber")
    private Integer accountNumber;
}
