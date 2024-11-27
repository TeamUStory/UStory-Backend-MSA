package me.ustory.api.paper.adapter.out.kafka;

import me.ustory.api.common.kafka.CreatePaperNotificationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePaperNotificationKafkaProducerTest {

    @Mock
    private KafkaTemplate<String, CreatePaperNotificationDTO> createPaperKafkaTemplate;

    @InjectMocks
    private CreatePaperNotificationKafkaProducer createPaperNotificationKafkaProducer;

    @DisplayName("Paper가 생성되었다고 Notification 서비스에 알린다.")
    @Test
    void sendCreatePaperNotification() {
        // given
        CreatePaperNotificationDTO dto = new CreatePaperNotificationDTO(1L, List.of(2L, 3L));

        // when
        createPaperNotificationKafkaProducer.sendCreatePaperNotification(dto);

        // then
        verify(createPaperKafkaTemplate).send(eq("create-paper-notification"), eq(dto));
    }

}