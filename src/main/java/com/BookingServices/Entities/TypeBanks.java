package com.BookingServices.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
public class TypeBanks {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nameType")
    private String nameType;

}
