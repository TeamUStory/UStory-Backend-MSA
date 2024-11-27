package me.ustory.api.common.kafka;

import java.util.List;

public record UnlockPaperNotificationDTO(
    Long paper,
    List<Long> memberIds
) {
}
