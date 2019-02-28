package com.example.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository cityrepos;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityrepos, RabbitTemplate rt) {
        this.cityrepos = cityrepos;
        this.rt = rt;
    }

    @GetMapping("/afford")
    public void findSome()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c: cities) {
            int priority = c.getAffordabilityIndex();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), priority, secret);

            log.info("Sending Message...");
            if (secret) {
                rt.convertAndSend(CitiesApplication.SECRET_QUEUE, message);
            } else if (priority < 6) {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES1, message);
            } else {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES2, message);
            }
        }
    }

    @GetMapping("/homes")
    public void findHomes() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c: cities) {
            int priority = c.getPrice();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), priority, secret);

            if (secret) {
                rt.convertAndSend(CitiesApplication.SECRET_QUEUE, message);
            } else if (priority > 200000) {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES1, message);
            } else {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES2, message);
            }
        }
    }

    @GetMapping("/names")
    public void findNames() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities) {
            int priority = c.getPrice();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), priority, secret);

            if (secret) {
                rt.convertAndSend(CitiesApplication.SECRET_QUEUE, message);
            } else {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES1, message);
            }
        }
    }
};
