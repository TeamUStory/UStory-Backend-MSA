package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.Paper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PaperMapper {

    public static PaperEntity mapToJpaEntity(Paper paper) {
        return PaperEntity.builder()
            .title(paper.getTitle())
            .writerId(paper.getWriter().getId())
            .diaryId(paper.getDiary().getId())
            .thumbnailImageUrl(paper.getThumbnailImageUrl())
            .store(paper.getStore())
            .visitedAt(paper.getVisitedAt())
            .unLocked(paper.isLocked())
            .build();
    }

}
