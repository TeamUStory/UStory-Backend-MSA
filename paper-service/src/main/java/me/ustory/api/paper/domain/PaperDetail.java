package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class PaperDetail {

    private final Images images;

    private final Address address;

    public static PaperDetail of(Images images, Address address) {
        return new PaperDetail(images, address);
    }

    private PaperDetail(Images images, Address address) {
        this.images = images;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperDetail that = (PaperDetail) o;
        return Objects.equals(images, that.images) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(images, address);
    }

    @Override
    public String toString() {
        return "PaperDetail{" +
            "images=" + images +
            ", address=" + address +
            '}';
    }
}
