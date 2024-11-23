package me.ustory.api.comment.adapter.in.web.response;

import me.ustory.api.comment.domain.Comment;

import java.time.LocalDate;

public record GetCommentResponse(
    Long commentId,
    String content,
    String nickname,
    String profileImageUrl,
    LocalDate createdAt
) {
    public static GetCommentResponse of(Comment comment) {
        return new GetCommentResponse(
            comment.getCommentId().getValue(),
            comment.getContent(),
            comment.getMemberInfo().getNickname(),
            comment.getMemberInfo().getProfile().getUrl(),
            comment.getCreatedAt().toLocalDate()
        );
    }
}
