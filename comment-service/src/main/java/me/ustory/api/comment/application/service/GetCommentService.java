package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.GetCommentCommand;
import me.ustory.api.comment.application.port.in.GetCommentUseCase;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.domain.Comment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentService implements GetCommentUseCase {

    private final GetCommentPort getCommentPort;

    @Override
    public Comment getComment(GetCommentCommand command) {
        return getCommentPort.getComment(command.commentId());
    }

}
