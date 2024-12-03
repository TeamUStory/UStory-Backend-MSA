package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.adapter.in.web.reqeust.UpdatePaperRequest;
import me.ustory.api.paper.domain.Coordinate;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.PaperId;

import java.time.LocalDate;
import java.util.List;

public record UpdatePaperCommand(
    PaperId paperId,
    MemberId updaterId,

    String title,
    Image thumbnail,
    LocalDate visitedAt,

    Images images,

    String city,
    String store,
    Coordinate coordinateX,
    Coordinate coordinateY
) {
    public static UpdatePaperCommand of(UpdatePaperRequest request, PaperId paperId, MemberId updaterId) {
        return new UpdatePaperCommand(
            paperId,
            updaterId,
            request.title(),
            Image.of(request.thumbnailImageUrl()),
            request.visitedAt(),
            Images.of(request.imageUrls()),
            request.city(),
            request.store(),
            Coordinate.latitude(request.coordinateX()),
            Coordinate.longitude(request.coordinateY())
        );
    }
}
