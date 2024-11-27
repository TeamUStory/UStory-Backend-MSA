package me.ustory.api.paper.adapter.in.kafka;

import me.ustory.api.common.kafka.UpdateDiaryKafkaDTO;
import me.ustory.api.paper.application.port.in.UpdateDiaryCommand;
import me.ustory.api.paper.application.port.in.UpdateDiaryUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateDiaryKafkaConsumerTest {

    @Mock
    private UpdateDiaryUseCase updateDiaryUseCase;

    @InjectMocks
    private UpdateDiaryKafkaConsumer updateDiaryKafkaConsumer;

    @DisplayName("update-diary 토픽을 받으면, diary 데이터 동기화 로직을 수행한다.")
    @Test
    void listener() {
        // given
        UpdateDiaryKafkaDTO dto = new UpdateDiaryKafkaDTO(1L, List.of(1L, 2L), "다이어리이름", "https://www.example.com/이미지.png", "#00001", "https://www.example.com/이미지2.png");

        // when
        updateDiaryKafkaConsumer.listener(dto);

        // then
        verify(updateDiaryUseCase).updateDiary(any(UpdateDiaryCommand.class));
    }
}