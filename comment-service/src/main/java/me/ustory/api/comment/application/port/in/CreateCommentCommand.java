package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.PaperId;

public record CreateCommentCommand(
    MemberId memberId,
    PaperId paperId,
    String content
) {
    public static CreateCommentCommand of(Long memberId, Long paperId, String content) {
        return new CreateCommentCommand(MemberId.of(memberId), PaperId.of(paperId), content);
    }
}
