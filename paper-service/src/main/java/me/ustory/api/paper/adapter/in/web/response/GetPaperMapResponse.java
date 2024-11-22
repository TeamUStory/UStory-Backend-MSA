package me.ustory.api.paper.adapter.in.web.response;

import me.ustory.api.paper.domain.Paper;

public record GetPaperMapResponse(
    Long paperId,

    String title,
    String thumbnailImageUrl,
    String store,

    Double coordinateX,
    Double coordinateY,

    String diaryColor,
    String diaryImageUrl,
    String diaryMarkerUrl
) {
    public static GetPaperMapResponse from(Paper paper) {
        return new GetPaperMapResponse(
            paper.getPaperId().getId(),

            paper.getTitle(),
            paper.getThumbnailUrl(),
            paper.getStore(),

            paper.getDetail().getAddress().getCoordinateXValue(),
            paper.getDetail().getAddress().getCoordinateYValue(),

            paper.getDiary().getColor(),
            paper.getDiary().getImage().getUrl(),
            paper.getDiary().getMarker().getUrl()
        );
    }
}
