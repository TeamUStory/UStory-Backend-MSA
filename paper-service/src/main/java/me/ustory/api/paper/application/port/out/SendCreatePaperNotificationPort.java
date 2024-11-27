package me.ustory.api.paper.application.port.out;

import me.ustory.api.common.kafka.CreatePaperNotificationDTO;

public interface SendCreatePaperNotificationPort {
    void sendCreatePaperNotification(CreatePaperNotificationDTO dto);
}
