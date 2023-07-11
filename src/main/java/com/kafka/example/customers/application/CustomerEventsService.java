package com.kafka.example.customers.application;

import com.kafka.example.customers.infra.port.out.MessagingBroker;
import com.kafka.example.customers.infra.port.out.MessagingEvent;
import com.kafka.example.events.domain.Customer;
import com.kafka.example.events.domain.CustomerCreatedEvent;
import com.kafka.example.events.domain.Event;
import com.kafka.example.events.domain.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CustomerEventsService implements MessagingEvent<Customer> {

    private MessagingBroker<ProducerFactory<String, Event<?>>, KafkaTemplate<String, Event<?>>> kafkaBroker;

    @Value("${topic.customer.name:customers}")
    private String topicCustomer;

    @Autowired
    public CustomerEventsService(MessagingBroker<ProducerFactory<String, Event<?>>, KafkaTemplate<String, Event<?>>> kafkaBroker) {
        this.kafkaBroker = kafkaBroker;
    }

    @Override
    public void publish(Customer customer) {
        CustomerCreatedEvent created = new CustomerCreatedEvent();

        created.setData(customer);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(LocalDate.now());

        kafkaBroker.brokerTemplate().send(topicCustomer, created);
    }
}
