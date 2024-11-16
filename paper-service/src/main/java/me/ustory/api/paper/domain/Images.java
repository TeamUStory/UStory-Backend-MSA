package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Images {

    private List<String> imageUrls;

    public static Images of(List<String> imageUrls) {
        return new Images(imageUrls);
    }

    private Images(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
