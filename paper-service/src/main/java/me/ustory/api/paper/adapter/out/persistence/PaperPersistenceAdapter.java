package me.ustory.api.paper.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PaperPersistenceAdapter implements CreatePaperPort {

    private final PaperJpaRepository paperJpaRepository;
    private final PaperDetailJpaRepository paperDetailJpaRepository;

    @Override
    public PaperId createPaper(Paper paper, PaperDetail paperDetail) {
        PaperEntity savedPaper = paperJpaRepository.save(PaperMapper.mapToJpaEntity(paper));
        Long paperId = savedPaper.getId();

        paperDetailJpaRepository.save(PaperDetailMapper.mapToJpaEntity(paperId, paperDetail));

        return PaperId.of(paperId);
    }
}
