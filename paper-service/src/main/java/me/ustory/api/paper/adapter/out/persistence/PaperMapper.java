package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PaperMapper {

    public static PaperEntity mapToJpaEntity(Paper paper) {
        return PaperEntity.builder()
            .title(paper.getTitle())
            .writerId(paper.getWriter().getId())
            .diaryInfo(DiaryInfoMapper.mapToEntity(paper.getDiary()))
            .thumbnailImageUrl(paper.getThumbnailUrl())
            .store(paper.getStore())
            .visitedAt(paper.getVisitedDate())
            .isLocked(paper.isLocked())
            .build();
    }

    public static Paper mapToDomain(PaperEntity paperEntity, PaperDetail paperDetail) {
        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            paperEntity.getTitle(),
            Image.of(paperEntity.getThumbnailImageUrl()),
            paperEntity.getStore(),
            paperEntity.getVisitedAt()
        );

        return Paper.builder()
            .paperId(PaperId.of(paperEntity.getId()))
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .diary(DiaryInfoMapper.mapToDomain(paperEntity.getDiaryInfo()))
            .locked(paperEntity.getIsLocked())
            .build();
    }
}
