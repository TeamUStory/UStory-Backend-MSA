package me.ustory.api.comment.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.UpdateMemberCommand;
import me.ustory.api.comment.application.port.in.UpdateMemberUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberKafkaConsumer {

    private final UpdateMemberUseCase updateMemberUseCase;

    @KafkaListener(topics = "update-member", groupId = "comment-service")
    public void listener(MemberKafkaResponse request) {
        UpdateMemberCommand command = UpdateMemberCommand.of(request);

        updateMemberUseCase.updateMember(command);
    }

}
