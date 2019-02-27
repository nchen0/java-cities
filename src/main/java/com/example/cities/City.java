package com.example.cities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City {
    private @Id @GeneratedValue Long id;
    private String name;
    private int price;
    private int affordabilityIndex;

    public City() {

    }

    public City(String name, int price, int affordabilityIndex) {
        this.name = name;
        this.price = price;
        this.affordabilityIndex = affordabilityIndex;
    }
}
