package me.ustory.api.comment.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.PaperId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CommentMapper {

    public static CommentEntity mapToEntity(Comment comment, MemberInfoEntity memberInfoEntity) {
        return CommentEntity.withoutId(comment.getPaperId().getValue(), memberInfoEntity, comment.getContent());
    }

    public static CommentEntity mapToEntityWithId(Comment comment, MemberInfoEntity memberInfoEntity) {
        return CommentEntity.withId(comment.getCommentId().getValue(), comment.getPaperId().getValue(), memberInfoEntity, comment.getContent());
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
