package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class Address {

    private String city;

    private Coordinate coordinateX;

    private Coordinate coordinateY;

    public static Address of(String city, Double coordinateX, Double coordinateY) {
        return new Address(city, Coordinate.latitude(coordinateX), Coordinate.longitude(coordinateY));
    }

    public double getCoordinateXValue() {
        return coordinateX.getValue();
    }

    public double getCoordinateYValue() {
        return coordinateY.getValue();
    }

    private Address(String city, Coordinate coordinateX, Coordinate coordinateY) {
        this.city = city;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

}
