package com.dmkcompany.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private Long userId;
    private String address;
    private String entrance;
    private String floor;
    private String flat;
    private String comment;
}