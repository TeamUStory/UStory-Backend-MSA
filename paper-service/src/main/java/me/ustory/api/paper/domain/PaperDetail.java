package me.ustory.api.paper.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaperDetail {

    private PaperId id;

    private Images images;

    private Address address;

    @Builder
    private PaperDetail(Images images, Address address) {
        this.images = images;
        this.address = address;
    }
}
