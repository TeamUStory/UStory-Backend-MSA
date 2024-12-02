package me.ustory.api.paper.adapter.in.kafka;

import me.ustory.api.common.kafka.CreateCommentKafkaDTO;
import me.ustory.api.paper.application.port.in.kafka.UnlockPaperCommand;
import me.ustory.api.paper.application.port.in.kafka.UnlockPaperUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentKafkaConsumerTest {

    @Mock
    private UnlockPaperUseCase unlockPaperUseCase;

    @InjectMocks
    private CommentKafkaConsumer commentKafkaConsumer;

    @DisplayName("create-comment 토픽을 받으면, paper 잠금 해제 로직을 수행한다.")
    @Test
    void listener() {
        // given
        CreateCommentKafkaDTO dto = new CreateCommentKafkaDTO(1L, 3);

        // when
        commentKafkaConsumer.listener(dto);

        // then
        verify(unlockPaperUseCase).unlockPaper(any(UnlockPaperCommand.class));
    }
}