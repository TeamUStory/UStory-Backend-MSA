package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.UpdateCommentCommand;
import me.ustory.api.comment.application.port.in.UpdateCommentUseCase;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.application.port.out.UpdateCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.common.exception.client.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentService implements UpdateCommentUseCase {

    private final GetCommentPort getCommentPort;
    private final UpdateCommentPort updateCommentPort;

    @Override
    public CommentId updateComment(UpdateCommentCommand command) {
        Comment comment = getCommentPort.getComment(command.commentId());

        if (comment.getMemberInfo().isNotSameMember(command.memberId())) {
            throw new ForbiddenException("댓글을 수정할 수 있는 권한이 없습니다.");
        }

        Comment updatedComment = comment.changeContent(command.content());

        return updateCommentPort.updateComment(updatedComment);
    }

}
