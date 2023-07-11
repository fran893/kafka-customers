package com.kafka.example.customers.infra.port.out;

public interface MessagingBroker<P, T> {

    public P producerFactory();

    public T brokerTemplate();

}
