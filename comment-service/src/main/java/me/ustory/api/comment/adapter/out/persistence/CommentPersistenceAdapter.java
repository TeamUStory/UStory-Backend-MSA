package me.ustory.api.comment.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.out.CreateCommentPort;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.application.port.out.UpdateCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.comment.domain.PaperId;
import org.springframework.stereotype.Component;

import java.util.List;

import static me.ustory.api.comment.adapter.out.persistence.QCommentEntity.commentEntity;

@Component
@RequiredArgsConstructor
class CommentPersistenceAdapter implements CreateCommentPort, GetCommentPort, UpdateCommentPort {

    private final JPAQueryFactory queryFactory;
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public CommentId createComment(Comment comment) {
        MemberInfo memberInfo = comment.getMemberInfo();
        MemberInfoEntity memberInfoEntity = MemberInfoEntity.withoutId(memberInfo.getNickname(), memberInfo.getProfile().getUrl());
        CommentEntity commentEntity = CommentEntity.withoutId(comment.getPaperId().getValue(), memberInfoEntity, comment.getContent());

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
    public CommentId updateComment(Comment comment) {
        MemberInfo memberInfo = comment.getMemberInfo();
        MemberInfoEntity memberInfoEntity = MemberInfoEntity.withId(memberInfo.getId().getValue(), memberInfo.getNickname(), memberInfo.getProfile().getUrl());

        CommentEntity commentEntity = commentJpaRepository.save(CommentMapper.mapToEntityWithId(comment, memberInfoEntity));

        return CommentId.of(commentEntity.getId());
    }
}
