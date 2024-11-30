package me.ustory.api.comment.adapter.out.feign;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.out.GetMemberFeignPort;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberFeignAdapter implements GetMemberFeignPort {

    private final MemberFeignClient memberFeignClient;

    @Override
    public MemberInfo getMemberInfoById(MemberId memberId) {
        MemberFeignDTO response = memberFeignClient.findMemberById(memberId.getValue());
        return MemberFeignMapper.mapToDomain(response);
    }

}
