package me.ustory.api.comment.application.service;

import me.ustory.api.comment.application.port.in.UpdateCommentCommand;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.application.port.out.UpdateCommentPort;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateCommentServiceTest {

    @Mock
    private GetCommentPort getCommentPort;

    @Mock
    private UpdateCommentPort updateCommentPort;

    @InjectMocks
    private UpdateCommentService updateCommentService;

    @DisplayName("댓글을 수정한다.")
    @Test
    void updateComment() {
        // given
        Long commentId = 1L;
        Long memberId = 1L;
        String content = "수정된 댓글";
        UpdateCommentCommand command = UpdateCommentCommand.of(commentId, memberId, content);

        MemberInfo memberInfo = MemberInfo.of(MemberId.of(memberId), "닉네임", Image.of("https://www.example.com/프로필.png"));
        Comment comment = Comment.withId(CommentId.of(commentId), PaperId.of(1L), memberInfo, "댓글", LocalDateTime.of(2024, 10, 10, 10, 10));
        given(getCommentPort.getComment(CommentId.of(commentId))).willReturn(comment);

        Comment expectedComment = Comment.withId(CommentId.of(commentId), PaperId.of(1L), memberInfo, content, LocalDateTime.of(2024, 10, 10, 10, 10));
        given(updateCommentPort.updateComment(expectedComment)).willReturn(expectedComment.getCommentId());

        // when
        CommentId updatedCommentId = updateCommentService.updateComment(command);

        // then
        assertThat(updatedCommentId).isEqualTo(expectedComment.getCommentId());

        verify(getCommentPort).getComment(CommentId.of(commentId));
        verify(updateCommentPort).updateComment(expectedComment);
    }
}