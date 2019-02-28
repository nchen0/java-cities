package com.example.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityMessageListener {
    @RabbitListener(queues = CitiesApplication.QUEUE_CITIES1)
    public void receiveMessage(CityMessage c) {
        log.info("Received Cities 1 Message: {}", c.toString());
    }

    @RabbitListener(queues = CitiesApplication.QUEUE_CITIES2)
    public void receiveMessage2(CityMessage c) {
        log.info("Received Cities 2 Message: {}", c.toString());
    }

    @RabbitListener(queues = CitiesApplication.SECRET_QUEUE)
    public void receiveMessageSecret(CityMessage c) {
        log.info("Received Secret Message: {}", c.toString());
    }
}
