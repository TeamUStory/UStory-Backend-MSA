package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PaperMapper {

    public static PaperEntity mapToJpaEntity(Paper paper) {
        return PaperEntity.builder()
            .title(paper.getTitle())
            .writerId(paper.getWriter().getValue())
            .diaryInfo(DiaryInfoMapper.mapToEntity(paper.getDiary()))
            .thumbnailImageUrl(paper.getThumbnailUrl())
            .store(paper.getStore())
            .visitedAt(paper.getVisitedDate())
            .isLocked(paper.isLocked())
            .build();
    }

    public static PaperEntity mapToJpaEntityWithId(Paper paper) {
        return PaperEntity.builder()
            .id(paper.getPaperId().getId())
            .title(paper.getTitle())
            .writerId(paper.getWriter().getValue())
            .diaryInfo(DiaryInfoMapper.mapToEntity(paper.getDiary()))
            .thumbnailImageUrl(paper.getThumbnailUrl())
            .store(paper.getStore())
            .visitedAt(paper.getVisitedDate())
            .isLocked(paper.isLocked())
            .build();
    }

    public static Paper mapToDomain(PaperEntity paperEntity) {
        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            paperEntity.getTitle(),
            Image.of(paperEntity.getThumbnailImageUrl()),
            paperEntity.getStore(),
            paperEntity.getVisitedAt()
        );

        return Paper.builder()
            .paperId(PaperId.of(paperEntity.getId()))
            .paperBasicInfo(paperBasicInfo)
            .writer(MemberId.of(paperEntity.getWriterId()))
            .diary(DiaryInfoMapper.mapToDomain(paperEntity.getDiaryInfo()))
            .locked(paperEntity.getIsLocked())
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
            .writer(MemberId.of(paperEntity.getWriterId()))
            .diary(DiaryInfoMapper.mapToDomain(paperEntity.getDiaryInfo()))
            .locked(paperEntity.getIsLocked())
            .build();
    }
}
