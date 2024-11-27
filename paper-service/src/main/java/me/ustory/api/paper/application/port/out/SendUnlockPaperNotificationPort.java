package me.ustory.api.paper.application.port.out;

import me.ustory.api.common.kafka.UnlockPaperNotificationDTO;

public interface SendUnlockPaperNotificationPort {
    void sendUnlockPaperNotification(UnlockPaperNotificationDTO dto);
}
