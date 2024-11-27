package me.ustory.api.comment.application.port.in;

import me.ustory.api.common.kafka.UpdateMemberKafkaDTO;
import me.ustory.api.comment.domain.Image;
import me.ustory.api.comment.domain.MemberId;

public record UpdateMemberCommand(
    MemberId memberId,
    String nickname,
    Image profile
) {
    public static UpdateMemberCommand of(UpdateMemberKafkaDTO request) {
        return new UpdateMemberCommand(
            MemberId.of(request.memberId()),
            request.nickname(),
            Image.of(request.profileImageUrl())
        );
    }
}
