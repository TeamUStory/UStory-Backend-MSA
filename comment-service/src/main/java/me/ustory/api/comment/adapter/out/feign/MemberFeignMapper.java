package me.ustory.api.comment.adapter.out.feign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.comment.domain.Image;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MemberFeignMapper {

    public static MemberInfo mapToDomain(MemberFeignResponse response) {
        return MemberInfo.of(MemberId.of(response.memberId()), response.nickname(), Image.of(response.profileImage()));
    }

}
