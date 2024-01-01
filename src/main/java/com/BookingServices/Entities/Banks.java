package com.BookingServices.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Table
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Banks {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankId;

    @Column(name = "nameBank")
    private String nameBank;

    @Column(name = "imageBank")
    private String imageBank;

    @Column(name = "typeBank")
    private Integer typeBank;
}
