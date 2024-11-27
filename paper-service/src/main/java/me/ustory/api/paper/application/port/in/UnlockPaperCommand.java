package me.ustory.api.paper.application.port.in;

import me.ustory.api.common.kafka.CommentKafkaRequest;
import me.ustory.api.paper.domain.PaperId;

public record UnlockPaperCommand(
    PaperId paperId,
    int commentCount
) {
    public static UnlockPaperCommand of(CommentKafkaRequest request) {
        return new UnlockPaperCommand(PaperId.of(request.paperId()), request.commentCount());
    }
}
