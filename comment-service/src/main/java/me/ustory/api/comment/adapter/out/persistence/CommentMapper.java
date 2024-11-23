package me.ustory.api.comment.adapter.out.persistence;

import me.ustory.api.comment.domain.Comment;

public class CommentMapper {

    public static CommentEntity mapToEntity(Comment comment, MemberInfoEntity memberInfoEntity) {
        return CommentEntity.withoutId(comment.getPaperId().getValue(), memberInfoEntity, comment.getContent());
    }
}
