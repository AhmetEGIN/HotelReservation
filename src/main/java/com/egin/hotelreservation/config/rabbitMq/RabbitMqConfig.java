package com.egin.hotelreservation.config.rabbitMq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * RabbitMQ, Message Broker'ını programımıza entegre etmemiz için
 * ihtiyaç duyulan Configürasyon dosyasıdır.
 * Gerekli olan exchange, queue, routingKey burada tanımlanır.
 *
 * @author EGIN
 */
@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.exchange.name}")
    String exchangeName;

    @Value("${spring.rabbitmq.queue.name}")
    String queueName;

    @Value("${spring.rabbitmq.routing.key}")
    String routingKeyForInvoiceService;

    @Bean
    public Exchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue queue() {
        Queue queue = new Queue(queueName, false, false, true);
        return queue;
    }


    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKeyForInvoiceService).noargs();
    }


    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        return new Jackson2JsonMessageConverter(objectMapper);

    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        return rabbitTemplate;
    }


}
