package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.domain.Coordinate;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberId;

import java.time.LocalDate;

public record CreatePaperCommand(
    String title,
    Image thumbnail,
    LocalDate visitedAt,
    MemberId writerId,
    DiaryId diaryId,

    Images imageUrls,

    String city,
    String store,
    Coordinate coordinateX,
    Coordinate coordinateY
) {
    public static CreatePaperCommand of(CreatePaperRequest request, Long writerId) {
        return new CreatePaperCommand(
            request.title(),
            Image.of(request.thumbnailImageUrl()),
            request.visitedAt(),
            MemberId.of(writerId),
            DiaryId.of(request.diaryId()),
            Images.of(request.imageUrls()),
            request.city(),
            request.store(),
            Coordinate.latitude(request.coordinateX()),
            Coordinate.longitude(request.coordinateY())
        );
    }
}
