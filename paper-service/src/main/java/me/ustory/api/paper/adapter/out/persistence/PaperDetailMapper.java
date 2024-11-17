package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.PaperDetail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PaperDetailMapper {

    public static PaperDetailEntity mapToJpaEntity(Long paperId, PaperDetail paperDetail) {
        return PaperDetailEntity.builder()
            .id(paperId)
            .address(mapToAddressEntity(paperDetail.getAddress()))
            .images(mapToImagesEntity(paperDetail.getImages()))
            .build();
    }

    private static AddressEntity mapToAddressEntity(Address address) {
        return AddressEntity.of(address.getCity(), address.getCoordinateXValue(), address.getCoordinateYValue());
    }

    private static ImagesEntity mapToImagesEntity(Images images) {
        return ImagesEntity.of(
            images.getImages().stream()
            .map(Image::getUrl)
            .toList()
        );
    }

}
