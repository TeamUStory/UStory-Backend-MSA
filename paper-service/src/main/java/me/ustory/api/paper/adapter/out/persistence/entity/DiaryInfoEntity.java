package me.ustory.api.paper.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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

    private String imageUrl;

    private String color;

    private String markerUrl;

    public static DiaryInfoEntity of(Long id, MembersInfoEntity membersInfo, String name, String imageUrl, String color, String markerUrl) {
        return new DiaryInfoEntity(id, membersInfo, name, imageUrl, color, markerUrl);
    }

    private DiaryInfoEntity(Long id, MembersInfoEntity memberIds, String name, String imageUrl, String color, String markerUrl) {
        this.id = id;
        this.members = memberIds;
        this.name = name;
        this.imageUrl = imageUrl;
        this.color = color;
        this.markerUrl = markerUrl;
    }
}
