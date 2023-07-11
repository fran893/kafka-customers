package com.kafka.example.customers.infra.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "customers")
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
