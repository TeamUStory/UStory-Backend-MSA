package me.ustory.api.paper.adapter.in.web.response;

import me.ustory.api.paper.domain.Paper;

import java.time.LocalDate;
import java.util.List;

public record GetPaperResponse(

    String title,

    String thumbnailImageUrl,

    List<String> imageUrls,

    LocalDate visitedAt,

    String city,

    String store,

    String diaryName,

    Boolean isLocked,

    // TODO: 수정 및 삭제 가능 여부 구현
    // Boolean isUpdatable,

    Double coordinateX,

    Double coordinateY
) {
    public static GetPaperResponse from(Paper paper) {
        return new GetPaperResponse(
            paper.getTitle(),
            paper.getThumbnailUrl(),
            paper.getDetail().getImages().getImageUrls(),
            paper.getVisitedDate(),
            paper.getDetail().getAddress().getCity(),
            paper.getStore(),
            paper.getDiary().getName(),
            paper.isLocked(),
            paper.getDetail().getAddress().getCoordinateXValue(),
            paper.getDetail().getAddress().getCoordinateYValue()
        );
    }
}
