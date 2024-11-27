package me.ustory.api.paper.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.kafka.CommentKafkaRequest;
import me.ustory.api.paper.application.port.in.UnlockPaperCommand;
import me.ustory.api.paper.application.port.in.UnlockPaperUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentKafkaConsumer {

    private final UnlockPaperUseCase unlockPaperUseCase;

    @KafkaListener(topics = "create-comment", groupId = "paper-service")
    public void listener(CommentKafkaRequest request) {
        UnlockPaperCommand command = UnlockPaperCommand.of(request);

        unlockPaperUseCase.unlockPaper(command);
    }

}
