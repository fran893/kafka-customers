package com.kafka.example.customers.infra.port.in;

import com.kafka.example.customers.infra.adapter.out.persistance.CustomerEntity;
import com.kafka.example.events.domain.Customer;

import java.util.List;

public interface CustomerPort {

    List<Customer> getCustomers();
    void saveCustomer(Customer customer);

}
