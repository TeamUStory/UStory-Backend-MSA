package me.ustory.api.comment.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.comment.domain.MemberInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MemberInfoMapper {

    public static MemberInfoEntity mapToEntity(MemberInfo memberInfo) {
        return MemberInfoEntity.withoutId(memberInfo.getNickname(), memberInfo.getProfile().getUrl());
    }

}
