package me.ustory.api.comment.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import me.ustory.api.comment.application.port.out.UnlockPaperPort;
import me.ustory.api.comment.domain.PaperId;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentKafkaProducer implements UnlockPaperPort {

    private final KafkaTemplate<String, CommentKafkaRequest> kafkaTemplate;

    public void createdComment(PaperId paperId, int commentCount) {
        kafkaTemplate.send("create-comment",new CommentKafkaRequest(paperId.getValue(), commentCount));
    }

}
