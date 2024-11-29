package me.ustory.api.comment.application.service;

import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.out.CreateCommentPort;
import me.ustory.api.comment.application.port.out.GetCommentPort;
import me.ustory.api.comment.application.port.out.GetMemberFeignPort;
import me.ustory.api.comment.application.port.out.UnlockPaperPort;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateCommentServiceTest {

    @Mock
    private GetMemberFeignPort getMemberFeignPort;

    @Mock
    private CreateCommentPort createCommentPort;

    @Mock
    private GetCommentPort getCommentPort;

    @Mock
    private UnlockPaperPort unlockPaperPort;

    @InjectMocks
    private CreateCommentService createCommentService;

    @DisplayName("회원정보를 불러오고, 댓글을 저장한다.")
    @Test
    void createComment() {
        // given
        Long memberId = 1L;
        Long paperId = 1L;
        String content = "댓글내용";
        CreateCommentCommand command = CreateCommentCommand.of(memberId, paperId, content);

        String nickname = "닉네임";
        Image profileImage = Image.of("https://www.example.com/프로필.png");
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(memberId), nickname, profileImage);
        given(getMemberFeignPort.getMemberInfoById(MemberId.of(memberId))).willReturn(memberInfo);

        Comment comment = Comment.withoutId(PaperId.of(paperId), memberInfo, content);
        CommentId commentId = CommentId.of(1L);
        given(createCommentPort.createComment(any(Comment.class))).willReturn(commentId);

        int commentCount = 1;
        given(getCommentPort.getCommentCount(PaperId.of(paperId))).willReturn(commentCount);

        // when
        CommentId createdCommentId = createCommentService.createComment(command);

        // then
        assertThat(createdCommentId).isEqualTo(commentId);

        verify(getMemberFeignPort).getMemberInfoById(MemberId.of(memberId));
        verify(createCommentPort).createComment(any(Comment.class));
        verify(unlockPaperPort).createdComment(PaperId.of(paperId), commentCount);
    }
}