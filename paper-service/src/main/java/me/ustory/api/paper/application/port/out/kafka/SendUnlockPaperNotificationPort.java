package me.ustory.api.paper.application.port.out.kafka;

import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;

public interface SendUnlockPaperNotificationPort {
    void sendUnlockPaperNotification(UnlockPaperNotificationKafkaDTO dto);
}
