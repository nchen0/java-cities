package com.example.cities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitiesApplication {

    public static final String EXCHANGE_NAME = "CityServer";
    public static final String QUEUE_CITIES1 = "Cities1Queue";
    public static final String QUEUE_CITIES2 = "Cities2Queue";
    public static final String SECRET_QUEUE = "SecretQueue";

    public static void main(String[] args) {
        SpringApplication.run(CitiesApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue Cities1Queue()
    {
        return new Queue(QUEUE_CITIES1);
    }

    @Bean
    public Binding declareBinding1()
    {
        return BindingBuilder.bind(Cities1Queue()).to(appExchange()).with(QUEUE_CITIES1);
    }

    @Bean
    public Queue Cities2Queue()
    {
        return new Queue(QUEUE_CITIES2);
    }

    @Bean
    public Binding declareBinding2()
    {
        return BindingBuilder.bind(Cities2Queue()).to(appExchange()).with(QUEUE_CITIES2);
    }

    @Bean
    public Queue SecretQueue()
    {
        return new Queue(SECRET_QUEUE);
    }

    @Bean
    public Binding declareBindingSecret()
    {
        return BindingBuilder.bind(SecretQueue()).to(appExchange()).with(SECRET_QUEUE);
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
