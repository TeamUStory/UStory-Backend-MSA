package me.ustory.api.paper.application.port.in.kafka;

public interface UnlockPaperUseCase {
    void unlockPaper(UnlockPaperCommand command);
}
