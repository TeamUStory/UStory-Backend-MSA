package me.ustory.api.paper.application.port.out;

import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;

public interface SendUnlockPaperNotificationPort {
    void sendUnlockPaperNotification(UnlockPaperNotificationKafkaDTO dto);
}
