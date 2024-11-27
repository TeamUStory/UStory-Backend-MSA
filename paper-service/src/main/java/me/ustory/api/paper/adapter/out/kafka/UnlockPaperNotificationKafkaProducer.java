package me.ustory.api.paper.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;
import me.ustory.api.paper.application.port.out.SendUnlockPaperNotificationPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnlockPaperNotificationKafkaProducer implements SendUnlockPaperNotificationPort {

    private final KafkaTemplate<String, UnlockPaperNotificationKafkaDTO> kafkaTemplate;

    @Override
    public void sendUnlockPaperNotification(UnlockPaperNotificationKafkaDTO dto) {
        kafkaTemplate.send("unlock-paper-notification", dto);
    }
}
