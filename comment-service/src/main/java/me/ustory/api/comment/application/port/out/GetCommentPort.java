package me.ustory.api.comment.application.port.out;

import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.PaperId;

import java.util.List;

public interface GetCommentPort {
    Comment getComment(CommentId id);
    List<Comment> getComments(PaperId id);
}
