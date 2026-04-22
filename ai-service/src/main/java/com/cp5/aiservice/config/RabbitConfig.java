package com.cp5.aiservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "adoption.exchange";
    public static final String REQUEST_QUEUE = "adoption.request.queue";
    public static final String RESPONSE_QUEUE = "adoption.response.queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(REQUEST_QUEUE);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(RESPONSE_QUEUE);
    }

    @Bean
    public Binding requestBinding(Queue requestQueue, TopicExchange exchange) {
        return BindingBuilder.bind(requestQueue)
                .to(exchange)
                .with("adoption.request");
    }

    @Bean
    public Binding responseBinding(Queue responseQueue, TopicExchange exchange) {
        return BindingBuilder.bind(responseQueue)
                .to(exchange)
                .with("adoption.response");
    }
}