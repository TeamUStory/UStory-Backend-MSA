package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.CommentId;

public interface CreateCommentUseCase {
    CommentId createComment(CreateCommentCommand command);
}
