package me.ustory.api.paper.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;
import me.ustory.api.paper.application.port.out.kafka.SendUnlockPaperNotificationPort;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UnlockPaperNotificationKafkaProducer implements SendUnlockPaperNotificationPort {

    private final KafkaTemplate<String, UnlockPaperNotificationKafkaDTO> kafkaTemplate;

    @Override
    public void sendUnlockPaperNotification(PaperId paperId, List<MemberId> memberIds) {
        UnlockPaperNotificationKafkaDTO request = new UnlockPaperNotificationKafkaDTO(
            paperId.getId(),
            memberIds.stream().map(MemberId::getValue).toList()
        );

        kafkaTemplate.send("unlock-paper-notification", request);
    }
}
