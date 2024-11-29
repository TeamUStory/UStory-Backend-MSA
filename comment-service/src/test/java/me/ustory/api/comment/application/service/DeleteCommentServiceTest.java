package me.ustory.api.comment.application.service;

import me.ustory.api.comment.application.port.in.DeleteCommentCommand;
import me.ustory.api.comment.application.port.out.DeleteCommentPort;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.comment.domain.PaperId;
import me.ustory.api.common.vo.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCommentServiceTest {

    @Mock
    private GetCommentPort getCommentPort;

    @Mock
    private DeleteCommentPort deleteCommentPort;

    @InjectMocks
    private DeleteCommentService deleteCommentService;

    @DisplayName("댓글을 삭제한다.")
    @Test
        void deleteComment() {
        // given
        CommentId commentId = CommentId.of(1L);
        MemberId memberId = MemberId.of(1L);

        MemberInfo memberInfo = MemberInfo.of(memberId, "닉네임", Image.of("https://www.example.com/프로필.png"));
        Comment comment = Comment.withId(commentId, PaperId.of(1L), memberInfo, "댓글", LocalDateTime.of(2024, 10, 10, 10, 10));
        given(getCommentPort.getComment(commentId)).willReturn(comment);

        DeleteCommentCommand command = DeleteCommentCommand.of(commentId.getValue(), memberId.getValue());

        // when
        deleteCommentService.deleteComment(command);

        // then
        verify(getCommentPort).getComment(commentId);
        verify(deleteCommentPort).deleteComment(commentId);
    }
}