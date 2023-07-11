package com.kafka.example.customers.infra.adapter.out.persistance;

import com.kafka.example.events.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements IMapper<Customer, CustomerEntity> {


    @Override
    public Customer entityToDomain(CustomerEntity customerEntity) {
        Customer customer = new Customer();

        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setEmail(customerEntity.getEmail());

        return customer;
    }

    @Override
    public CustomerEntity domainToEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());

        return customerEntity;
    }
}
