package me.ustory.api.comment.adapter.out.kafka;

public record CommentKafkaRequest(
    Long paperId,
    int commentCount
){
}
