package me.ustory.api.comment.adapter.out.persistence;

import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.PaperId;

public class CommentMapper {

    public static CommentEntity mapToEntity(Comment comment, MemberInfoEntity memberInfoEntity) {
        return CommentEntity.withoutId(comment.getPaperId().getValue(), memberInfoEntity, comment.getContent());
    }

    public static Comment mapToDomain(CommentEntity commentEntity) {
        return Comment.withId(
            CommentId.of(commentEntity.getId()),
            PaperId.of(commentEntity.getPaperId()),
            MemberInfoMapper.mapToDomain(commentEntity.getMemberInfo()),
            commentEntity.getContent(),
            commentEntity.getCreatedAt()
        );
    }
}
