package me.ustory.api.paper.adapter.out.feign;

import java.util.List;

public record DiaryFeignResponse(
    Long diaryId,
    List<Long> membersId,
    String name,
    String imageUrl,
    String color,
    String markerUrl
) {
}
