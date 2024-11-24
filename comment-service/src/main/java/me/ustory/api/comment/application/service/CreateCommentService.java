package me.ustory.api.comment.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.in.CreateCommentUseCase;
import me.ustory.api.comment.application.port.out.CreateCommentPort;
import me.ustory.api.comment.application.port.out.GetMemberFeignPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService implements CreateCommentUseCase {

    private final CreateCommentPort createCommentPort;
    private final GetMemberFeignPort getMemberFeignPort;

    @Override
    public CommentId createComment(CreateCommentCommand command) {
        MemberInfo memberInfo = getMemberFeignPort.getMemberInfoById(command.memberId());

        Comment comment = Comment.withoutId(command.paperId(), memberInfo, command.content());

        return createCommentPort.createComment(comment);
    }

}
