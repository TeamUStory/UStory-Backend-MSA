package me.ustory.api.paper.adapter.out.persistence;

import static me.ustory.api.paper.adapter.out.persistence.QPaperDetailEntity.paperDetailEntity;
import static me.ustory.api.paper.adapter.out.persistence.QPaperEntity.paperEntity;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.application.port.out.GetPaperPort;
import me.ustory.api.paper.application.port.out.UpdatePaperPort;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
class PaperPersistenceAdapter implements CreatePaperPort, GetPaperPort, UpdatePaperPort {

    private final JPAQueryFactory queryFactory;
    private final PaperJpaRepository paperJpaRepository;
    private final PaperDetailJpaRepository paperDetailJpaRepository;

    @Override
    @Transactional
    public PaperId createPaper(Paper paper) {
        PaperEntity savedPaper = paperJpaRepository.save(PaperMapper.mapToJpaEntity(paper));
        Long paperId = savedPaper.getId();

        paperDetailJpaRepository.save(PaperDetailMapper.mapToJpaEntity(paperId, paper.getDetail()));

        return PaperId.of(paperId);
    }

    @Override
    public Paper findById(PaperId id) {
        PaperEntity paperEntity = paperJpaRepository.findById(id.getId()).orElseThrow();
        PaperDetailEntity paperDetailEntity = paperDetailJpaRepository.findById(id.getId()).orElseThrow();

        PaperDetail paperDetail = PaperDetailMapper.mapToDomain(paperDetailEntity);

        return PaperMapper.mapToDomain(paperEntity, paperDetail);
    }

    @Override
    public List<Paper> findByWriterId(MemberId writerId, PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.page() - 1, paginationRequest.size());
        List<PaperEntity> paperEntities = queryFactory.selectFrom(paperEntity)
            .where(paperEntity.writerId.eq(writerId.getValue()),
                paperEntity.createdAt.loe(paginationRequest.requestTime()),
                paperEntity.deletedAt.isNull())
            .orderBy(paperEntity.createdAt.desc())
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();

        return paperEntities.stream()
            .map(PaperMapper::mapToDomain)
            .toList();
    }

    @Override
    public int findCountByWriterId(MemberId memberId) {
        Long count = queryFactory.select(paperEntity.count())
            .from(paperEntity)
            .where(paperEntity.writerId.eq(memberId.getValue()))
            .fetchOne();

        return count == null ? 0 : count.intValue();
    }

    @Override
    public List<Paper> findByMemberId(MemberId memberId) {
        List<Tuple> result = queryFactory.select(paperEntity, paperDetailEntity)
            .from(paperEntity)
                .join(paperDetailEntity)
                .on(paperEntity.id.eq(paperDetailEntity.id))
            .where(paperEntity.diaryInfo.members.memberIds.contains(memberId.getValue()),
                paperEntity.deletedAt.isNull())
            .orderBy(paperEntity.createdAt.desc())
            .fetch();

        if (result.isEmpty()) {
            return List.of();
        }

        return result.stream()
            .map(tuple -> {
                PaperEntity tuplePaperEntity = tuple.get(paperEntity);
                PaperDetailEntity tupleDetailEntity = tuple.get(paperDetailEntity);
                return PaperMapper.mapToDomain(
                    tuplePaperEntity,
                    PaperDetailMapper.mapToDomain(tupleDetailEntity)
                );
            })
            .toList();
    }

    @Override
    public List<Paper> findByDiaryId(DiaryId diaryId, PaginationRequest paginationRequest, LocalDate startDate, LocalDate endDate) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.page() - 1, paginationRequest.size());
        List<PaperEntity> paperEntities = queryFactory.selectFrom(paperEntity)
            .where(paperEntity.diaryInfo.id.eq(diaryId.getValue()),
                startDateCondition(startDate),
                endDateCondition(endDate),
                paperEntity.createdAt.loe(paginationRequest.requestTime()),
                paperEntity.deletedAt.isNull())
            .orderBy(paperEntity.createdAt.desc())
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();

        return paperEntities.stream()
            .map(PaperMapper::mapToDomain)
            .toList();
    }

    @Override
    @Transactional
    public PaperId updatePaper(Paper paper) {
        paperJpaRepository.save(PaperMapper.mapToJpaEntityWithId(paper));
        paperDetailJpaRepository.save(PaperDetailMapper.mapToJpaEntity(paper.getPaperId().getId(), paper.getDetail()));
        return paper.getPaperId();
    }

    private BooleanExpression startDateCondition(LocalDate startDate) {
        return startDate != null ? paperEntity.createdAt.goe(startDate.atStartOfDay()) : null;
    }

    private BooleanExpression endDateCondition(LocalDate endDate) {
        return endDate != null ? paperEntity.createdAt.loe(endDate.plusDays(1).atStartOfDay().minusNanos(1)) : null;
    }
}
