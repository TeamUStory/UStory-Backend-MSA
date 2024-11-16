package me.ustory.api.paper.application.port.in;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
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

}
