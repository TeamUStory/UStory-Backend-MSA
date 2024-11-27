package me.ustory.api.comment.application.port.out;

import me.ustory.api.comment.domain.MemberInfo;

public interface UpdateMemberPort {
    void updateMember(MemberInfo memberInfo);
}
