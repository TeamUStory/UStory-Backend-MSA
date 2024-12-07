package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Address {

    private final String city;

    private final Coordinate coordinateX;

    private final Coordinate coordinateY;

    public static Address of(String city, Double coordinateX, Double coordinateY) {
        return new Address(city, Coordinate.latitude(coordinateX), Coordinate.longitude(coordinateY));
    }

    public static Address of(String city, Coordinate coordinateX, Coordinate coordinateY) {
        if (!coordinateX.isLatitude() || !coordinateY.isLongitude()) {
            throw new IllegalArgumentException("coordinateX and coordinateY must be latitude/longitude");
        }

        return new Address(city, coordinateX, coordinateY);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(coordinateX, address.coordinateX) && Objects.equals(coordinateY, address.coordinateY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return "Address{" +
            "city='" + city + '\'' +
            ", coordinateX=" + coordinateX +
            ", coordinateY=" + coordinateY +
            '}';
    }
}
