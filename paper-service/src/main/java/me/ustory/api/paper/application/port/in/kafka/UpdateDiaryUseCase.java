package me.ustory.api.paper.application.port.in.kafka;

public interface UpdateDiaryUseCase {
    void updateDiary(UpdateDiaryCommand command);
}
