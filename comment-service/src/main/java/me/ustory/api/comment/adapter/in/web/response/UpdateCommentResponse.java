package me.ustory.api.comment.adapter.in.web.response;

import me.ustory.api.comment.domain.CommentId;

public record UpdateCommentResponse(
    Long commentId
) {
    public static UpdateCommentResponse of(CommentId commentId) {
        return new UpdateCommentResponse(commentId.getValue());
    }
}
