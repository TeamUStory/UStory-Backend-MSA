package me.ustory.api.comment.adapter.out.feign;

record MemberFeignDTO(
    Long memberId,
    String nickname,
    String profileImage
) {
}
