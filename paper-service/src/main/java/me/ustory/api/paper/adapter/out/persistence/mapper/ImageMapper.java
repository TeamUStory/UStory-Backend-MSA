package me.ustory.api.paper.adapter.out.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.domain.Images;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageMapper {

    public static Image mapToDomain(String image) {
        return Image.of(image);
    }

    public static Images mapToImages(List<String> images) {
        return Images.of(images);
    }
}
