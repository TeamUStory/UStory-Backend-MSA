package me.ustory.api.comment.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCommentRequest(
    @NotBlank(message = "댓글 내용은 필수 값 입니다.")
    String content
) {
}
