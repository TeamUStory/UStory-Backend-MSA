package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class PaperDetail {

    private Images images;

    private Address address;

    public static PaperDetail of(Images images, Address address) {
        return new PaperDetail(images, address);
    }

    private PaperDetail(Images images, Address address) {
        this.images = images;
        this.address = address;
    }
}
