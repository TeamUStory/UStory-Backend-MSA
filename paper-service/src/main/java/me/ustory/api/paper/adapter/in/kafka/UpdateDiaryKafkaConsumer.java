package me.ustory.api.paper.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.kafka.UpdateDiaryKafkaDTO;
import me.ustory.api.paper.application.port.in.UpdateDiaryCommand;
import me.ustory.api.paper.application.port.in.UpdateDiaryUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateDiaryKafkaConsumer {

    private final UpdateDiaryUseCase updateDiaryUseCase;

    @KafkaListener(topics = "update-diary", groupId = "paper-service")
    public void listener(UpdateDiaryKafkaDTO dto) {
        UpdateDiaryCommand command = UpdateDiaryCommand.of(dto);
        updateDiaryUseCase.updateDiary(command);
    }

}
