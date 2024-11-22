package me.ustory.api.paper.adapter.in.web.reqeust;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CreatePaperRequest(

    @NotNull(message = "페이퍼의 제목은 필수입니다. 제목을 작성하여 주세요.")
    String title,

    @NotNull(message = "페이퍼의 썸네일은 필수입니다. 썸네일을 지정해 주세요.")
    String thumbnailImageUrl,

    List<String> imageUrls,

    @NotNull(message = "페이퍼의 방문일은 필수입니다. 방문일을 지정해 주세요.")
    LocalDate visitedAt,

    @NotNull(message = "다이어리 지정은 필수입니다. 다이어리를 지정해 주세요.")
    Long diaryId,

    @NotNull(message = "장소는 필수입니다. 장소를 지정해 주세요.")
    String city,

    @NotNull(message = "상호명은 필수입니다. 상호명을 지정해 주세요.")
    String store,

    @NotNull(message = "좌표값은 필수입니다. 좌표를 지정해 주세요.")
    Double coordinateX,

    @NotNull(message = "좌표값은 필수입니다. 좌표를 지정해 주세요.")
    Double coordinateY

) {

}
