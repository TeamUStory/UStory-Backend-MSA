package me.ustory.api.paper.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.kafka.UnlockPaperNotificationDTO;
import me.ustory.api.paper.application.port.out.SendUnlockPaperNotificationPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnlockPaperNotificationKafkaProducer implements SendUnlockPaperNotificationPort {

    private final KafkaTemplate<String, UnlockPaperNotificationDTO> kafkaTemplate;

    @Override
    public void sendUnlockPaperNotification(UnlockPaperNotificationDTO dto) {
        kafkaTemplate.send("unlock-paper-notification", dto);
    }
}
