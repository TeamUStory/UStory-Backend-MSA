package me.ustory.api.comment.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.adapter.in.web.request.CreateCommentRequest;
import me.ustory.api.comment.adapter.in.web.response.CreateCommentResponse;
import me.ustory.api.comment.adapter.in.web.response.GetCommentResponse;
import me.ustory.api.comment.application.port.in.CreateCommentCommand;
import me.ustory.api.comment.application.port.in.CreateCommentUseCase;
import me.ustory.api.comment.application.port.in.GetCommentCommand;
import me.ustory.api.comment.application.port.in.GetCommentUseCase;
import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.common.controller.response.ApiResponse;
import me.ustory.api.common.controller.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final GetCommentUseCase getCommentUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateCommentResponse>> createComment(
        @RequestParam("memberId") Long memberId,
        @Valid @RequestBody CreateCommentRequest request
    ) {
        CreateCommentCommand command = CreateCommentCommand.of(memberId, request.paperId(), request.content());

        CommentId commentId = createCommentUseCase.createComment(command);

        CreateCommentResponse response = new CreateCommentResponse(commentId.getValue());

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(SuccessResponse.success(response));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponse<GetCommentResponse>> getComment(
        @PathVariable(name = "commentId") Long commentId
    ) {
        GetCommentCommand command = GetCommentCommand.of(commentId);

        Comment comment = getCommentUseCase.getComment(command);

        GetCommentResponse response = GetCommentResponse.of(comment);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(response));
    }

    @GetMapping
    public void getComments(
        @RequestParam("paperId") Long paperId
    ) {

    }

    @PutMapping("/{commentId}")
    public void updateComment(
        @PathVariable(name = "commentId") Long commentId,
        @RequestParam("memberId") Long memberId
    ) {

    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
        @PathVariable(name = "commentId") Long commentId,
        @RequestParam("memberId") Long memberId
    ) {

    }
}
