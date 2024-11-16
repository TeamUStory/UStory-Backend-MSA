package me.ustory.api.paper.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Address {

    private String city;

    private Double coordinateX;

    private Double coordinateY;

    @Builder
    private Address(String city, Double coordinateX, Double coordinateY) {
        this.city = city;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

}
