package com.dmkcompany.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courier {
    private Long id;
    private String fullName;
    private String phone;
    private String status; // свободен, занят
}