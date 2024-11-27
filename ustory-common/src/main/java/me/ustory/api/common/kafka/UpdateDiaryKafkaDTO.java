package me.ustory.api.common.kafka;

import java.util.List;

public record UpdateDiaryKafkaDTO(
    Long diaryId,
    List<Long> memberIds,
    String diaryName,
    String imageUrl,
    String color,
    String markerUrl
) {
}
