package com.example.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
public class CityController {

    private final CityRepository cityrepos;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityrepos, RabbitTemplate rt) {
        this.cityrepos = cityrepos;
        this.rt = rt;
    }

    @GetMapping("/cities")
    public void findSome()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c: cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending Message...");
            if (rand <= 5)
            {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES1, message);
            }
            else
            {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES1, message);
            }
        }
    }
}
