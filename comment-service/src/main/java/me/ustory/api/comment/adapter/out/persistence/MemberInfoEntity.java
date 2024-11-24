package me.ustory.api.comment.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ustory.api.common.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MemberInfoEntity extends BaseEntity {

    @Id
    private Long id;

    private String nickname;

    private String profileImage;

    public static MemberInfoEntity withoutId(String nickname, String profileImage) {
        return new MemberInfoEntity(null, nickname, profileImage);
    }

    public static MemberInfoEntity withId(Long id, String nickname, String profileImage) {
        return new MemberInfoEntity(id, nickname, profileImage);
    }

    private MemberInfoEntity(Long id, String nickname, String profileImage) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

}
