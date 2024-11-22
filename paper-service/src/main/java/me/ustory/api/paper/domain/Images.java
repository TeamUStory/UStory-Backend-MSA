package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Images images1 = (Images) o;
        return Objects.equals(images, images1.images);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(images);
    }

    @Override
    public String toString() {
        return "Images{" +
            "images=" + images +
            '}';
    }
}
