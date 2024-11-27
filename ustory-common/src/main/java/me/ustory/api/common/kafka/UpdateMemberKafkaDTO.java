package me.ustory.api.common.kafka;

public record UpdateMemberKafkaDTO(
    Long memberId,
    String nickname,
    String profileImageUrl
) {
}
