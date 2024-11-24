package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.Comment;

import java.util.List;

public interface GetCommentUseCase {
    Comment getComment(GetCommentCommand command);
    List<Comment> getComments(GetCommentsCommand command);
}
