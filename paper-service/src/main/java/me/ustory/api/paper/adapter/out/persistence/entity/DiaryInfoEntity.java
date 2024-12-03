package me.ustory.api.paper.adapter.out.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ustory.api.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class DiaryInfoEntity extends BaseEntity {

    @Id
    private Long id;

    private MembersInfoEntity members;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity image;

    private String color;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity marker;

    public static DiaryInfoEntity of(Long id, MembersInfoEntity membersInfo, String name, ImageEntity image, String color, ImageEntity marker) {
        return new DiaryInfoEntity(id, membersInfo, name, image, color, marker);
    }

    private DiaryInfoEntity(Long id, MembersInfoEntity memberIds, String name, ImageEntity image, String color, ImageEntity marker) {
        this.id = id;
        this.members = memberIds;
        this.name = name;
        this.image = image;
        this.color = color;
        this.marker = marker;
    }
}
