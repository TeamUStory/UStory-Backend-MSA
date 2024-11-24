package me.ustory.api.comment.adapter.out.feign;

record MemberFeignResponse(
    Long memberId,
    String nickname,
    String profileImage
) {
}
