package com.kafka.example.customers.application;

import com.kafka.example.customers.domain.Customer;
import com.kafka.example.customers.infra.adapter.out.persistance.CustomerEntity;
import com.kafka.example.customers.infra.adapter.out.persistance.IMapper;
import com.kafka.example.customers.infra.port.in.CustomerPort;
import com.kafka.example.customers.infra.port.out.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements CustomerPort {

    private CustomerRepository customerRepository;
    private IMapper<Customer, CustomerEntity> mapper;
    private CustomerEventsService customerEventsService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, IMapper<Customer, CustomerEntity> mapper,
                           CustomerEventsService customerEventsService) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.customerEventsService = customerEventsService;
    }


    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customerEntity -> {
            customers.add(mapper.entityToDomain(customerEntity));
        });

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        CustomerEntity customerEntity = mapper.domainToEntity(customer);

        customerEventsService.publish(customer);

        customerRepository.save(customerEntity);
    }
}
