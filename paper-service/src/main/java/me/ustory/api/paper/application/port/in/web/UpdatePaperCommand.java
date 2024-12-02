package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.paper.adapter.in.web.reqeust.UpdatePaperRequest;
import me.ustory.api.paper.domain.PaperId;

import java.time.LocalDate;
import java.util.List;

public record UpdatePaperCommand(
    PaperId paperId,
    Long updateUserId,

    String title,
    String thumbnailImageUrl,
    LocalDate visitedAt,

    List<String> imageUrls,

    String city,
    String store,
    Double coordinateX,
    Double coordinateY
) {
    public static UpdatePaperCommand of(UpdatePaperRequest request, PaperId paperId, Long userId) {
        return new UpdatePaperCommand(
            paperId,
            userId,
            request.title(),
            request.thumbnailImageUrl(),
            request.visitedAt(),
            request.imageUrls(),
            request.city(),
            request.store(),
            request.coordinateX(),
            request.coordinateY()
        );
    }
}
