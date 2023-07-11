package com.kafka.example.customers.domain;

import lombok.Data;

@Data
public class Customer {

    private Long id;
    private String name;
    private String email;

}
