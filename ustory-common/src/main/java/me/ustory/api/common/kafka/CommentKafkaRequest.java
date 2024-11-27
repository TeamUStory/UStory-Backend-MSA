package me.ustory.api.common.kafka;

public record CommentKafkaRequest(
    Long paperId,
    int commentCount
) {
}
