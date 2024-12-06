package me.ustory.api.paper.adapter.out.persistence.mapper;

import me.ustory.api.paper.adapter.out.persistence.entity.AddressEntity;
import me.ustory.api.paper.domain.Address;

public class AddressMapper {

    public static AddressEntity mapToEntity(String store, Address address) {
        return AddressEntity.of(store, address.getCity(), address.getCoordinateXValue(), address.getCoordinateYValue());
    }

}
