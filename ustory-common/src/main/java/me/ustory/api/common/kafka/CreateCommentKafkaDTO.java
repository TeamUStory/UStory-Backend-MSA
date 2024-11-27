package me.ustory.api.common.kafka;

public record CreateCommentKafkaDTO(
    Long paperId,
    int commentCount
) {
}
