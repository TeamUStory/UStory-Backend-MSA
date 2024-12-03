package me.ustory.api.paper.adapter.out.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.adapter.out.persistence.entity.AddressEntity;
import me.ustory.api.paper.adapter.out.persistence.entity.PaperDetailEntity;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.PaperDetail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaperDetailMapper {

    public static PaperDetailEntity mapToJpaEntity(Long paperId, PaperDetail paperDetail) {
        return PaperDetailEntity.builder()
            .id(paperId)
            .address(mapToAddressEntity(paperDetail.getAddress()))
            .images(ImageMapper.mapToJpaEntities(paperDetail.getImages()))
            .build();
    }

    public static PaperDetail mapToDomain(PaperDetailEntity paperDetailEntity) {
        return PaperDetail.of(
            ImageMapper.mapToImages(paperDetailEntity.getImages()),
            mapToAddressDomain(paperDetailEntity.getAddress())
        );
    }

    private static AddressEntity mapToAddressEntity(Address address) {
        return AddressEntity.of(address.getCity(), address.getCoordinateXValue(), address.getCoordinateYValue());
    }

    private static Address mapToAddressDomain(AddressEntity addressEntity) {
        return Address.of(addressEntity.getCity(), addressEntity.getCoordinateX(), addressEntity.getCoordinateY());
    }
}
