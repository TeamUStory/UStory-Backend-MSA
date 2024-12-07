package me.ustory.api.paper.domain;

public enum CoordinateStatus {

    LATITUDE("위도"),
    LONGITUDE("경도");

    final String description;

    CoordinateStatus(String description) {
        this.description = description;
    }

    public boolean isLatitude() {
        return this.equals(LATITUDE);
    }

    public boolean isLongitude() {
        return this.equals(LONGITUDE);
    }
}
