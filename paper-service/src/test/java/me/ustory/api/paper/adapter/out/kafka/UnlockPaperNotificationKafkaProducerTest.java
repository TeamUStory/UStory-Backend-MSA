package me.ustory.api.paper.adapter.out.kafka;

import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UnlockPaperNotificationKafkaProducerTest {

    @Mock
    private KafkaTemplate<String, UnlockPaperNotificationKafkaDTO> unlockPaperKafkaTemplate;

    @InjectMocks
    private UnlockPaperNotificationKafkaProducer unlockPaperNotificationKafkaProducer;

    @DisplayName("Paper가 잠금 해제되었다고 Notification 서비스에 알린다.")
    @Test
    void sendUnlockPaperNotification() {
        // given
        UnlockPaperNotificationKafkaDTO dto = new UnlockPaperNotificationKafkaDTO(1L, List.of(2L, 3L));

        // when
        unlockPaperNotificationKafkaProducer.sendUnlockPaperNotification(dto);

        // then
        verify(unlockPaperKafkaTemplate).send(eq("unlock-paper-notification"), eq(dto));
    }
}