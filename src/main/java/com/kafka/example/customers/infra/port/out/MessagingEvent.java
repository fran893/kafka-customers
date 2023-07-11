package com.kafka.example.customers.infra.port.out;

public interface MessagingEvent<T> {

    void publish(T t);

}
