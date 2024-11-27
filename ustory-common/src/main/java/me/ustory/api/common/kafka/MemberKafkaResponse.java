package me.ustory.api.common.kafka;

public record MemberKafkaResponse(
    Long memberId,
    String nickname,
    String profileImageUrl
) {
}
