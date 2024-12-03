package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.vo.Image;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageMapper {

    public static ImageEntity mapToJpaEntity(Image image) {
        return ImageEntity.of(image.getUrl());
    }

    public static Image mapToDomain(ImageEntity imageEntity) {
        return Image.of(imageEntity.getUrl());
    }
}
