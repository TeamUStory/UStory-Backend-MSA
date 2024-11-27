package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.in.CreateCommentUseCase;
import me.ustory.api.comment.application.port.out.CreateCommentPort;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.application.port.out.GetMemberFeignPort;
import me.ustory.api.comment.application.port.out.UnlockPaperPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.comment.domain.PaperId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService implements CreateCommentUseCase {

    private final CreateCommentPort createCommentPort;
    private final GetCommentPort getCommentPort;
    private final GetMemberFeignPort getMemberFeignPort;
    private final UnlockPaperPort unlockPaperPort;

    @Override
    public CommentId createComment(CreateCommentCommand command) {
        MemberInfo memberInfo = getMemberFeignPort.getMemberInfoById(command.memberId());

        Comment comment = Comment.withoutId(command.paperId(), memberInfo, command.content());

        CommentId commentId = createCommentPort.createComment(comment);

        sendCreatedComment(command.paperId());

        return commentId;
    }

    private void sendCreatedComment(PaperId paperId) {
        int commentCount = getCommentPort.getCommentCount(paperId);

        unlockPaperPort.createdComment(paperId, commentCount);
    }

}
