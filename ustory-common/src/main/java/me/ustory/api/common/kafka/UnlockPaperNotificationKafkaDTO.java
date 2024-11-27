package me.ustory.api.common.kafka;

import java.util.List;

public record UnlockPaperNotificationKafkaDTO(
    Long paper,
    List<Long> memberIds
) {
}
