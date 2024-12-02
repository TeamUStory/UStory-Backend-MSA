package me.ustory.api.paper.application.port.out.kafka;

import me.ustory.api.common.kafka.CreatePaperNotificationKafkaDTO;

public interface SendCreatePaperNotificationPort {
    void sendCreatePaperNotification(CreatePaperNotificationKafkaDTO dto);
}
