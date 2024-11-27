package me.ustory.api.common.kafka;

import java.util.List;

public record CreatePaperNotificationDTO(
    Long paperId,
    List<Long> memberIds
) {
}
