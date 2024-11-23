package me.ustory.api.comment.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ustory.api.comment.adapter.in.web.request.CreateCommentRequest;
import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.in.CreateCommentUseCase;
import me.ustory.api.comment.application.port.in.GetCommentCommand;
import me.ustory.api.comment.application.port.in.GetCommentUseCase;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.Image;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.comment.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateCommentUseCase createCommentUseCase;

    @MockBean
    private GetCommentUseCase getCommentUseCase;

    @DisplayName("댓글을 작성한다.")
    @Test
    void createComment() throws Exception {
        // given
        Long memberId = 1L;
        Long paperId = 1L;
        Long commentId = 1L;
        String content = "댓글";

        CreateCommentRequest request = new CreateCommentRequest(paperId, content);
        CreateCommentCommand command = CreateCommentCommand.of(memberId, paperId, content);

        given(createCommentUseCase.createComment(command)).willReturn(CommentId.of(commentId));

        // when & then
        mockMvc.perform(post("/api/comments")
                .param("memberId", String.valueOf(memberId))
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.commentId").value(commentId));

        verify(createCommentUseCase).createComment(command);
    }

    @DisplayName("댓글을 Id로 불러온다.")
    @Test
    void getComment() throws Exception {
        // given
        Long commentId = 1L;
        Long paperId = 1L;
        Long memberId = 1L;
        GetCommentCommand command = GetCommentCommand.of(memberId);

        String nickname = "닉네임";
        Image profileImage = Image.of("https://www.example.come/이미지.png");
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(memberId), nickname, profileImage);
        String content = "댓글 내용";
        LocalDateTime createdAt = LocalDateTime.of(2024, 10, 10, 10, 10);
        Comment comment = Comment.withId(CommentId.of(commentId), PaperId.of(paperId), memberInfo, content, createdAt);
        given(getCommentUseCase.getComment(command)).willReturn(comment);

        // when & then
        mockMvc.perform(get("/api/comments/{commentId}", commentId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.commentId").value(commentId))
            .andExpect(jsonPath("$.data.content").value(content))
            .andExpect(jsonPath("$.data.nickname").value(nickname))
            .andExpect(jsonPath("$.data.profileImageUrl").value(profileImage.getUrl()))
            .andExpect(jsonPath("$.data.createdAt").value(createdAt.toLocalDate().toString()));
    }
}