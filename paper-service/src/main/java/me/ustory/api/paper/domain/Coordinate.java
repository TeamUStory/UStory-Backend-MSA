package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class Coordinate {

    private final Double value;

    public static Coordinate latitude(Double latitude) {
        validateLatitude(latitude);
        return new Coordinate(latitude);
    }

    public static Coordinate longitude(Double longitude) {
        validateLongitude(longitude);
        return new Coordinate(longitude);
    }

    private Coordinate(Double value) {
        this.value = value;
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

}
