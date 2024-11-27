package me.ustory.api.comment.adapter.in.kafka;

public record MemberKafkaResponse(
    Long memberId,
    String nickname,
    String profileImageUrl
) {
}
