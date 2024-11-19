package me.ustory.api.paper.adapter.out.persistence;

import static me.ustory.api.paper.adapter.out.persistence.QPaperEntity.paperEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.application.port.out.GetPaperPort;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PaperPersistenceAdapter implements CreatePaperPort, GetPaperPort {

    private final JPAQueryFactory queryFactory;
    private final PaperJpaRepository paperJpaRepository;
    private final PaperDetailJpaRepository paperDetailJpaRepository;

    @Override
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
    public List<Paper> findByWriterId(Long writerId, PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.page() - 1, paginationRequest.size());
        List<PaperEntity> paperEntities =  queryFactory.selectFrom(paperEntity)
            .where(paperEntity.writerId.eq(writerId),
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
}
