package me.ustory.api.comment.application.port.out;

import me.ustory.api.comment.domain.CommentId;

public interface DeleteCommentPort {
    void deleteComment(CommentId commentId);
}
