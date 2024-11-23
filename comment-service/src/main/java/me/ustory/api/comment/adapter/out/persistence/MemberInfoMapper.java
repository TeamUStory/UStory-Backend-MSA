package me.ustory.api.comment.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.comment.domain.Image;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MemberInfoMapper {

    public static MemberInfoEntity mapToEntity(MemberInfo memberInfo) {
        return MemberInfoEntity.withoutId(memberInfo.getNickname(), memberInfo.getProfile().getUrl());
    }

    public static MemberInfo mapToDomain(MemberInfoEntity memberInfo) {
        return MemberInfo.of(
            MemberId.of(memberInfo.getId()),
            memberInfo.getNickname(),
            Image.of(memberInfo.getProfileImage())
        );
    }
}
