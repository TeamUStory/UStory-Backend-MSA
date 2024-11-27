package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.UpdateMemberCommand;
import me.ustory.api.comment.application.port.in.UpdateMemberUseCase;
import me.ustory.api.comment.application.port.out.UpdateMemberPort;
import me.ustory.api.comment.domain.MemberInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMemberInfoService implements UpdateMemberUseCase {

    private final UpdateMemberPort updateMemberPort;

    @Override
    public void updateMember(UpdateMemberCommand command) {
        MemberInfo memberInfo = MemberInfo.of(command.memberId(), command.nickname(), command.profile());
        updateMemberPort.updateMember(memberInfo);
    }

}
