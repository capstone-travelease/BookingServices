package com.BookingServices.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "password")
    private String password;

    @Column(name = "dob")
    private LocalDate birthday;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role_id")
    private int role;
}
