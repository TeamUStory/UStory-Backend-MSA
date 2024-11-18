package me.ustory.api.paper.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.application.port.out.GetPaperPort;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PaperPersistenceAdapter implements CreatePaperPort, GetPaperPort {

    private final PaperJpaRepository paperJpaRepository;
    private final PaperDetailJpaRepository paperDetailJpaRepository;
    private final DiaryInfoJpaRepository diaryInfoJpaRepository;

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
}
