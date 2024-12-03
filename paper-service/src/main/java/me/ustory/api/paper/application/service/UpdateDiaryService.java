package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.in.kafka.UpdateDiaryCommand;
import me.ustory.api.paper.application.port.in.kafka.UpdateDiaryUseCase;
import me.ustory.api.paper.application.port.out.persistence.GetDiaryPort;
import me.ustory.api.paper.application.port.out.persistence.UpdateDiaryPort;
import me.ustory.api.paper.domain.DiaryInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateDiaryService implements UpdateDiaryUseCase {

    private final GetDiaryPort getDiaryPort;
    private final UpdateDiaryPort updateDiaryPort;

    @Override
    public void updateDiary(UpdateDiaryCommand command) {
        if (getDiaryPort.isExistDiary(command.diaryId())) {

            DiaryInfo diaryInfo = DiaryInfo.of(
                command.diaryId(),
                command.members(),
                command.name(),
                command.image(),
                command.color(),
                command.marker()
            );

            updateDiaryPort.updateDiary(diaryInfo);
        }
    }
}
