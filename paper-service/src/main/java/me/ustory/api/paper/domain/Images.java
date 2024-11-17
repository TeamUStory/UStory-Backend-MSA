package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Images {

    private final List<Image> images;

    public static Images of(List<String> images) {
        return new Images(images.stream()
            .map(Image::of)
            .toList());
    }

    public List<String> getImagesUrl() {
        return images.stream()
            .map(Image::getUrl)
            .toList();
    }

    private Images(List<Image> images) {
        this.images = images;
    }

}
