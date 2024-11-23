package me.ustory.api.comment.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(

    @NotNull(message = "Paper Id는 필수 값 입니다.")
    Long paperId,

    @NotBlank(message = "댓글 내용은 필수 값 입니다.")
    String content

) {
}
