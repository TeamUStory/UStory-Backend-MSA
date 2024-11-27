package me.ustory.api.paper.application.port.out;

import me.ustory.api.common.kafka.CreatePaperNotificationKafkaDTO;

public interface SendCreatePaperNotificationPort {
    void sendCreatePaperNotification(CreatePaperNotificationKafkaDTO dto);
}
