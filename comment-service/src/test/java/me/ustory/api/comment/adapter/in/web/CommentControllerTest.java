package me.ustory.api.comment.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ustory.api.comment.adapter.in.web.request.CreateCommentRequest;
import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.in.CreateCommentUseCase;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
}