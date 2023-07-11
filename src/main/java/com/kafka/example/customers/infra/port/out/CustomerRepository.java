package com.kafka.example.customers.infra.port.out;

import com.kafka.example.customers.infra.adapter.out.persistance.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
