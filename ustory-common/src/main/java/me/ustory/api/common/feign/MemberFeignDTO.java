package me.ustory.api.common.feign;

public record MemberFeignDTO(
    Long memberId,
    String nickname,
    String profileImage
) {
}
