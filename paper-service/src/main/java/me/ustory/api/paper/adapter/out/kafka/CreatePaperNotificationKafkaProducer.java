package me.ustory.api.paper.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.kafka.CreatePaperNotificationKafkaDTO;
import me.ustory.api.paper.application.port.out.kafka.SendCreatePaperNotificationPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePaperNotificationKafkaProducer implements SendCreatePaperNotificationPort {

    private final KafkaTemplate<String, CreatePaperNotificationKafkaDTO> kafkaTemplate;

    @Override
    public void sendCreatePaperNotification(CreatePaperNotificationKafkaDTO dto) {
        kafkaTemplate.send("create-paper-notification", dto);
    }
}
