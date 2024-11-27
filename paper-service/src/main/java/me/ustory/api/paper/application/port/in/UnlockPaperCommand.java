package me.ustory.api.paper.application.port.in;

import me.ustory.api.common.kafka.CreateCommentKafkaDTO;
import me.ustory.api.paper.domain.PaperId;

public record UnlockPaperCommand(
    PaperId paperId,
    int commentCount
) {
    public static UnlockPaperCommand of(CreateCommentKafkaDTO dto) {
        return new UnlockPaperCommand(PaperId.of(dto.paperId()), dto.commentCount());
    }
}
