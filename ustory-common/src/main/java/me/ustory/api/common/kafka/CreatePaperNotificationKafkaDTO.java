package me.ustory.api.common.kafka;

import java.util.List;

public record CreatePaperNotificationKafkaDTO(
    Long paperId,
    List<Long> memberIds
) {
}
