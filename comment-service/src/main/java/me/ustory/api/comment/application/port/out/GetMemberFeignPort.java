package me.ustory.api.comment.application.port.out;

import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;

public interface GetMemberFeignPort {
    MemberInfo getMemberInfoById(MemberId memberId);
}
