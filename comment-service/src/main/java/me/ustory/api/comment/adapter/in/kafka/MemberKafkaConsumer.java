package me.ustory.api.comment.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.UpdateMemberCommand;
import me.ustory.api.comment.application.port.in.UpdateMemberUseCase;
import me.ustory.api.common.kafka.UpdateMemberKafkaDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberKafkaConsumer {

    private final UpdateMemberUseCase updateMemberUseCase;

    @KafkaListener(topics = "update-member", groupId = "comment-service")
    public void listener(UpdateMemberKafkaDTO dto) {
        UpdateMemberCommand command = UpdateMemberCommand.of(dto);

        updateMemberUseCase.updateMember(command);
    }

}
