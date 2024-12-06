package me.ustory.api.paper.adapter.out.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.adapter.out.persistence.entity.AddressEntity;
import me.ustory.api.paper.adapter.out.persistence.entity.PaperEntity;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaperMapper {

    public static PaperEntity mapToJpaEntity(Paper paper) {
        return PaperEntity.of(
            null,
            paper.getWriter().getValue(),
            DiaryInfoMapper.mapToEntity(paper.getDiary()),
            paper.getTitle(),
            paper.getThumbnailUrl(),
            paper.getBasicInfo().getVisitedAt(),
            paper.getDetail().getImages().getImageUrls(),
            AddressEntity.of(
                paper.getStore(),
                paper.getDetail().getAddress().getCity(),
                paper.getDetail().getAddress().getCoordinateXValue(),
                paper.getDetail().getAddress().getCoordinateYValue()),
            paper.isLocked());
    }

    public static PaperEntity mapToJpaEntityWithId(Paper paper) {
        return PaperEntity.of(
            paper.getPaperId().getId(),
            paper.getWriter().getValue(),
            DiaryInfoMapper.mapToEntity(paper.getDiary()),
            paper.getTitle(),
            paper.getThumbnailUrl(),
            paper.getBasicInfo().getVisitedAt(),
            paper.getDetail().getImages().getImageUrls(),
            AddressEntity.of(
                paper.getStore(),
                paper.getDetail().getAddress().getCity(),
                paper.getDetail().getAddress().getCoordinateXValue(),
                paper.getDetail().getAddress().getCoordinateYValue()),
            paper.isLocked());
    }

    public static Paper mapToDomain(PaperEntity paperEntity) {
        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            paperEntity.getTitle(),
            ImageMapper.mapToDomain(paperEntity.getThumbnail()),
            paperEntity.getAddress().getStore(),
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

    public static Paper mapToDomainWithDetail(PaperEntity paperEntity) {
        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            paperEntity.getTitle(),
            ImageMapper.mapToDomain(paperEntity.getThumbnail()),
            paperEntity.getAddress().getStore(),
            paperEntity.getVisitedAt()
        );

        PaperDetail paperDetail = PaperDetail.of(
            Images.of(paperEntity.getImages()),
            Address.of(paperEntity.getAddress().getCity(), paperEntity.getAddress().getCoordinateX(), paperEntity.getAddress().getCoordinateY())
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
