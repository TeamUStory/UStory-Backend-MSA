package me.ustory.api.comment.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.out.CreateCommentPort;
import me.ustory.api.comment.application.port.out.DeleteCommentPort;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.application.port.out.UpdateCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.PaperId;
import org.springframework.stereotype.Component;

import java.util.List;

import static me.ustory.api.comment.adapter.out.persistence.QCommentEntity.commentEntity;

@Component
@RequiredArgsConstructor
class CommentPersistenceAdapter implements CreateCommentPort, GetCommentPort, UpdateCommentPort, DeleteCommentPort {

    private final JPAQueryFactory queryFactory;
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public CommentId createComment(Comment comment) {
        MemberInfoEntity memberInfoEntity = MemberInfoMapper.mapToEntity(comment.getMemberInfo());
        CommentEntity commentEntity = CommentMapper.mapToEntity(comment, memberInfoEntity);

        CommentEntity savedCommentEntity = commentJpaRepository.save(commentEntity);

        return CommentId.of(savedCommentEntity.getId());
    }

    @Override
    public Comment getComment(CommentId id) {
        return CommentMapper.mapToDomain(commentJpaRepository.findById(id.getValue()).orElseThrow());
    }

    @Override
    public List<Comment> getComments(PaperId id) {
        List<CommentEntity> commentEntities = queryFactory.selectFrom(commentEntity)
            .where(commentEntity.paperId.eq(id.getValue()))
            .orderBy(commentEntity.createdAt.desc())
            .fetch();

        return commentEntities.stream().map(CommentMapper::mapToDomain).toList();
    }

    @Override
    public int getCommentCount(PaperId id) {
        Long count = queryFactory
            .select(commentEntity.count())
            .from(commentEntity)
            .where(commentEntity.paperId.eq(id.getValue()))
            .fetchOne();

        return count != null ? count.intValue() : 0;
    }

    @Override
    public CommentId updateComment(Comment comment) {
        MemberInfoEntity memberInfoEntity = MemberInfoMapper.mapToEntity(comment.getMemberInfo());

        CommentEntity commentEntity = commentJpaRepository.save(CommentMapper.mapToEntityWithId(comment, memberInfoEntity));

        return CommentId.of(commentEntity.getId());
    }

    @Override
    public void deleteComment(CommentId commentId) {
        commentJpaRepository.deleteById(commentId.getValue());
    }
}
