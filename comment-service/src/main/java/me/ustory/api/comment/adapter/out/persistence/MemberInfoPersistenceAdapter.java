package me.ustory.api.comment.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.out.UpdateMemberPort;
import me.ustory.api.comment.domain.MemberInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberInfoPersistenceAdapter implements UpdateMemberPort {

    private final MemberInfoJpaRepository memberInfoJpaRepository;

    @Override
    public void updateMember(MemberInfo memberInfo) {
        memberInfoJpaRepository.save(MemberInfoMapper.mapToEntity(memberInfo));
    }
}
