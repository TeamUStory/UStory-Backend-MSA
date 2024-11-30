package me.ustory.api.common.feign;

import java.util.List;

public record DiaryFeignDTO(
    Long diaryId,
    List<Long> membersId,
    String name,
    String imageUrl,
    String color,
    String markerUrl
) {
}
