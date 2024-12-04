package me.ustory.api.paper.adapter.out.persistence.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ustory.api.common.BaseEntity;
import me.ustory.api.paper.adapter.out.persistence.StringListConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PaperDetailEntity extends BaseEntity {

    @Id
    private Long id;

    @Convert(converter = StringListConverter.class)
    private List<String> images;

    @Embedded
    private AddressEntity address;

}
