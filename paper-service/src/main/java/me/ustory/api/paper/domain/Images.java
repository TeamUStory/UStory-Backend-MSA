package me.ustory.api.paper.domain;

import java.util.List;
import java.util.Objects;

public class Images {

    private final List<Image> imageList;

    public static Images of(List<String> images) {
        return new Images(images.stream()
            .map(Image::of)
            .toList());
    }

    public List<String> getImageUrls() {
        return imageList.stream()
            .map(Image::getUrl)
            .toList();
    }

    private Images(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Images images1 = (Images) o;
        return Objects.equals(imageList, images1.imageList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imageList);
    }

    @Override
    public String toString() {
        return "Images{" +
            "images=" + imageList +
            '}';
    }
}
