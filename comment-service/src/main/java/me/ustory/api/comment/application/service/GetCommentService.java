package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.GetCommentCommand;
import me.ustory.api.comment.application.port.in.GetCommentUseCase;
import me.ustory.api.comment.application.port.in.GetCommentsCommand;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentService implements GetCommentUseCase {

    private final GetCommentPort getCommentPort;

    @Override
    public Comment getComment(GetCommentCommand command) {
        return getCommentPort.getComment(command.commentId());
    }

    @Override
    public List<Comment> getComments(GetCommentsCommand command) {
        return getCommentPort.getComments(command.paperId());
    }

}
