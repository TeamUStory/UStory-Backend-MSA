package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.DeleteCommentCommand;
import me.ustory.api.comment.application.port.in.DeleteCommentUseCase;
import me.ustory.api.comment.application.port.out.DeleteCommentPort;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.common.exception.client.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService implements DeleteCommentUseCase {

    private final GetCommentPort getCommentPort;
    private final DeleteCommentPort deleteCommentPort;

    @Override
    public void deleteComment(DeleteCommentCommand command) {
        Comment comment = getCommentPort.getComment(command.commentId());

        if(comment.getMemberInfo().isNotSameMember(command.memberId())) {
            throw new ForbiddenException("댓글을 삭제할 수 있는 권한이 없습니다.");
        }

        deleteCommentPort.deleteComment(comment.getCommentId());
    }

}
