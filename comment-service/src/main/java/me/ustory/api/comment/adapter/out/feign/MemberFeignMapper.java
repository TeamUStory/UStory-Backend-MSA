package me.ustory.api.comment.adapter.out.feign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.feign.MemberFeignDTO;
import me.ustory.api.common.vo.Image;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MemberFeignMapper {

    public static MemberInfo mapToDomain(MemberFeignDTO response) {
        return MemberInfo.of(MemberId.of(response.memberId()), response.nickname(), Image.of(response.profileImage()));
    }

}
