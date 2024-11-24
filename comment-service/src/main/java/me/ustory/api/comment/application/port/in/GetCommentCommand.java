package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.CommentId;

public record GetCommentCommand(
    CommentId commentId
) {
    public static GetCommentCommand of(Long commentId) {
        return new GetCommentCommand(CommentId.of(commentId));
    }
}
