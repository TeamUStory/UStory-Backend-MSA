package me.ustory.api.paper.adapter.out.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.adapter.out.persistence.entity.ImageEntity;
import me.ustory.api.paper.domain.Images;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageMapper {

    public static ImageEntity mapToJpaEntity(Image image) {
        return ImageEntity.of(image.getUrl());
    }

    public static Image mapToDomain(ImageEntity imageEntity) {
        return Image.of(imageEntity.getUrl());
    }

    public static List<ImageEntity> mapToJpaEntities(Images images) {
        return images.getImageUrls().stream().map(ImageEntity::of).toList();
    }

    public static Images mapToImages(List<ImageEntity> imageEntities) {
        return Images.of(imageEntities.stream().map(ImageEntity::getUrl).toList());
    }
}
