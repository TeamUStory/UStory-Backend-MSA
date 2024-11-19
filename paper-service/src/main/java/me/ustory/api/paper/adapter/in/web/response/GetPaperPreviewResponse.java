package me.ustory.api.paper.adapter.in.web.response;

import me.ustory.api.paper.domain.Paper;

public record GetPaperPreviewResponse(

    String title,

    String thumbnailImageUrl,

    String diaryName,

    String store,

    Long paperId
) {
    public static GetPaperPreviewResponse of(Paper paper) {
        return new GetPaperPreviewResponse(
            paper.getTitle(),
            paper.getThumbnailUrl(),
            paper.getDiary().getName(),
            paper.getStore(),
            paper.getPaperId().getId()
        );
    }
}
