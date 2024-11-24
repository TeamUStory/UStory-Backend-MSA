package me.ustory.api.comment.application.service;

import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.in.GetCommentCommand;
import me.ustory.api.comment.application.port.in.GetCommentsCommand;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.Image;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.comment.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetCommentServiceTest {

    @Mock
    private GetCommentPort getCommentPort;

    @InjectMocks
    private GetCommentService getCommentService;

    @DisplayName("댓글을 불러온다.")
    @Test
    void getComment() {
        // given
        Long commentId = 1L;

        GetCommentCommand command = GetCommentCommand.of(commentId);

        Long memberId = 1L;
        String nickname = "닉네임";
        Image profileImage = Image.of("https://www.example.com/프로필.png");
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(memberId), nickname, profileImage);

        Long paperId = 1L;
        String content = "댓글내용";
        LocalDateTime createdAt = LocalDateTime.of(2024, 10, 10, 10, 10);
        Comment expectedComment = Comment.withId(CommentId.of(commentId), PaperId.of(paperId), memberInfo, content, createdAt);
        given(getCommentPort.getComment(CommentId.of(commentId))).willReturn(expectedComment);

        // when
        Comment comment = getCommentService.getComment(command);

        // then
        assertThat(comment.getCommentId().getValue()).isEqualTo(commentId);
        assertThat(comment.getPaperId().getValue()).isEqualTo(paperId);
        assertThat(comment.getMemberInfo()).isEqualTo(memberInfo);
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment.getCreatedAt()).isEqualTo(createdAt);

        verify(getCommentPort).getComment(CommentId.of(commentId));
    }

    @DisplayName("Paper에 속한 모든 댓글을 불러온다.")
    @Test
    void getComments() {
        // given
        Long paperId = 1L;

        GetCommentsCommand command = GetCommentsCommand.of(paperId);

        List<Comment> expectedComments = List.of(
            getComment(CommentId.of(1L), PaperId.of(paperId)),
            getComment(CommentId.of(2L), PaperId.of(paperId))
        );

        given(getCommentPort.getComments(PaperId.of(paperId))).willReturn(expectedComments);

        // when
        List<Comment> comments = getCommentService.getComments(command);

        // then
        assertThat(comments.get(0).getCommentId()).isEqualTo(expectedComments.get(0).getCommentId());
        assertThat(comments.get(0).getPaperId().getValue()).isEqualTo(paperId);
        assertThat(comments.get(0).getMemberInfo()).isEqualTo(expectedComments.get(0).getMemberInfo());
        assertThat(comments.get(0).getContent()).isEqualTo(expectedComments.get(0).getContent());
        assertThat(comments.get(0).getCreatedAt()).isEqualTo(expectedComments.get(0).getCreatedAt());

        assertThat(comments.get(1).getCommentId()).isEqualTo(expectedComments.get(1).getCommentId());
        assertThat(comments.get(1).getPaperId().getValue()).isEqualTo(paperId);
        assertThat(comments.get(1).getMemberInfo()).isEqualTo(expectedComments.get(1).getMemberInfo());
        assertThat(comments.get(1).getContent()).isEqualTo(expectedComments.get(1).getContent());
        assertThat(comments.get(1).getCreatedAt()).isEqualTo(expectedComments.get(1).getCreatedAt());

        verify(getCommentPort).getComments(PaperId.of(paperId));
    }

    private Comment getComment(CommentId commentId, PaperId paperId) {
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/프로필.png"));
        String content = "내용";
        LocalDateTime createdAt = LocalDateTime.of(2024, 10, 10, 10, 10);

        return Comment.withId(commentId, paperId, memberInfo, content, createdAt);
    }
}