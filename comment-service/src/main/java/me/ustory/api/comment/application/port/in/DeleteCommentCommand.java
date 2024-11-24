package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberId;

public record DeleteCommentCommand(
    CommentId commentId,
    MemberId memberId
) {
    public static DeleteCommentCommand of(Long commentId, Long memberId) {
        return new DeleteCommentCommand(CommentId.of(commentId), MemberId.of(memberId));
    }
}
