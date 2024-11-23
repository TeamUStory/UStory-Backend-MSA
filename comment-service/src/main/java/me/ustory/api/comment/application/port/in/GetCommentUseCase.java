package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.Comment;

public interface GetCommentUseCase {
    Comment getComment(GetCommentCommand command);
}
