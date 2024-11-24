package me.ustory.api.comment.application.port.out;

import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;

public interface UpdateCommentPort {

    CommentId updateComment(Comment comment);

}
