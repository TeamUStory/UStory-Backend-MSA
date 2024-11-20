package me.ustory.api.paper.adapter.out.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ImagesEntity {

    @ElementCollection
    @CollectionTable(name = "images_entity", joinColumns = @JoinColumn(name = "paper_detail_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    public static ImagesEntity of(List<String> imageUrls) {
        return new ImagesEntity(imageUrls);
    }

    private ImagesEntity(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrls() {
        return Collections.unmodifiableList(imageUrls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagesEntity that = (ImagesEntity) o;
        return Objects.equals(imageUrls, that.imageUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imageUrls);
    }

    @Override
    public String toString() {
        return "ImagesEntity{" +
            "imageUrls=" + imageUrls +
            '}';
    }
}
