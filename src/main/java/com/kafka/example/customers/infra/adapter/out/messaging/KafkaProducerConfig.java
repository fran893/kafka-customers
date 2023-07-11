package com.kafka.example.customers.infra.adapter.out.messaging;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.kafka.example.customers.domain.events.Event;
import com.kafka.example.customers.infra.port.out.MessagingBroker;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig implements MessagingBroker<ProducerFactory<String, Event<?>>, KafkaTemplate<String, Event<?>>> {

    private final String bootstrapAddress = "localhost:9092";

    @Bean
    @Override
    public ProducerFactory<String, Event<?>> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @Override
    public KafkaTemplate<String, Event<?>> brokerTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
