package me.ustory.api.paper.application.port.in;

import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;

import java.time.LocalDate;
import java.util.List;

public record CreatePaperCommand(
    String title,
    String thumbnailImageUrl,
    LocalDate visitedAt,
    Long writerId,
    Long diaryId,

    List<String> imageUrls,

    String city,
    String store,
    Double coordinateX,
    Double coordinateY
) {
    public static CreatePaperCommand of(CreatePaperRequest request, Long writerId) {
        return new CreatePaperCommand(
            request.title(),
            request.thumbnailImageUrl(),
            request.visitedAt(),
            writerId,
            request.diaryId(),
            request.imageUrls(),
            request.city(),
            request.store(),
            request.coordinateX(),
            request.coordinateY()
        );
    }
}
