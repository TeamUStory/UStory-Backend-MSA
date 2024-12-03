package me.ustory.api.paper.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressEntity {

    @Column(name = "city", nullable = false, columnDefinition = "varchar(300)")
    private String city;

    @Column(name = "coordinate_x", nullable = false, columnDefinition = "decimal(17,15)")
    private Double coordinateX;

    @Column(name = "coordinate_y", nullable = false, columnDefinition = "decimal(18,15)")
    private Double coordinateY;

    public static AddressEntity of(String city, Double coordinateX, Double coordinateY) {
        return new AddressEntity(city, coordinateX, coordinateY);
    }

    private AddressEntity(String city, Double coordinateX, Double coordinateY) {
        this.city = city;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(city, that.city) && Objects.equals(coordinateX, that.coordinateX) && Objects.equals(coordinateY, that.coordinateY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
            "city='" + city + '\'' +
            ", coordinateX=" + coordinateX +
            ", coordinateY=" + coordinateY +
            '}';
    }
}
