package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberId;

public record UpdateCommentCommand(
    CommentId commentId,
    MemberId memberId,
    String content
) {
    public static UpdateCommentCommand of(Long commentId, Long memberId, String content) {
        return new UpdateCommentCommand(CommentId.of(commentId), MemberId.of(memberId), content);
    }
}
