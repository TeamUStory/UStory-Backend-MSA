package me.ustory.api.paper.adapter.out.feign;

public record DiaryFeignResponse(
    Long diaryId,
    String name,
    String imageUrl,
    String color,
    String markerUrl,
    String category
) {
}
