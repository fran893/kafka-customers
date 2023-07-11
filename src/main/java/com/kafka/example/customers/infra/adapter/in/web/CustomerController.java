package com.kafka.example.customers.infra.adapter.in.web;

import com.kafka.example.customers.infra.port.in.CustomerPort;
import com.kafka.example.events.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class CustomerController {

    private CustomerPort customerPort;

    @Autowired
    public CustomerController(CustomerPort customerPort) {
        this.customerPort = customerPort;
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerPort.getCustomers(), HttpStatus.OK);
    }

    @PostMapping(value = "/customer")
    public void saveCustomer(@RequestBody Customer customer) {
        customerPort.saveCustomer(customer);
    }

}
