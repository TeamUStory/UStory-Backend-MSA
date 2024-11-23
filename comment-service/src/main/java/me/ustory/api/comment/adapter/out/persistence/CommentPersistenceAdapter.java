package me.ustory.api.comment.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.out.CreateCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CommentPersistenceAdapter implements CreateCommentPort {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public CommentId createComment(Comment comment) {
        MemberInfo memberInfo = comment.getMemberInfo();
        MemberInfoEntity memberInfoEntity = MemberInfoEntity.withoutId(memberInfo.getNickname(), memberInfo.getProfile().getUrl());
        CommentEntity commentEntity = CommentEntity.withoutId(comment.getPaperId().getValue(), memberInfoEntity, comment.getContent());

        CommentEntity savedCommentEntity = commentJpaRepository.save(commentEntity);

        return CommentId.of(savedCommentEntity.getId());
    }

}
