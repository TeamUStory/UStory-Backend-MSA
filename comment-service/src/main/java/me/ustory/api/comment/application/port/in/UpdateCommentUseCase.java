package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.CommentId;

public interface UpdateCommentUseCase {
    CommentId updateComment(UpdateCommentCommand command);
}
