package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Coordinate {

    private final Double value;
    private final CoordinateStatus status;

    public static Coordinate latitude(Double latitude) {
        validateLatitude(latitude);
        return new Coordinate(latitude, CoordinateStatus.LATITUDE);
    }

    public static Coordinate longitude(Double longitude) {
        validateLongitude(longitude);
        return new Coordinate(longitude, CoordinateStatus.LONGITUDE);
    }

    public boolean isLatitude() {
        return status.isLatitude();
    }

    public boolean isLongitude() {
        return status.isLongitude();
    }

    private Coordinate(Double value, CoordinateStatus status) {
        this.value = value;
        this.status = status;
    }

    private static void validateLatitude(Double value) {
        if (value == null || value < -90 || value > 90) {
            throw new IllegalArgumentException("위도는 -90 이상 90 이하여야 합니다.");
        }
    }

    private static void validateLongitude(Double value) {
        if (value == null || value < -180 || value > 180) {
            throw new IllegalArgumentException("경도는 -180 이상 180 이하여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
            "value=" + value +
            '}';
    }
}
