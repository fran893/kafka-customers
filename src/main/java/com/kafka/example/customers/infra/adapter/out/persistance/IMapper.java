package com.kafka.example.customers.infra.adapter.out.persistance;

public interface IMapper <T,Z> {

    T entityToDomain(Z z);

    Z domainToEntity(T t);

}
