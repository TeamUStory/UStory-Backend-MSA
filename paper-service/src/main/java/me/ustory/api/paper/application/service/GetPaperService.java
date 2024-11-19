package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.in.GetPaperCommand;
import me.ustory.api.paper.application.port.in.GetPaperUseCase;
import me.ustory.api.paper.application.port.in.GetWrittenPapersCommand;
import me.ustory.api.paper.application.port.out.GetPaperPort;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetPaperService implements GetPaperUseCase {

    private final GetPaperPort getPaperPort;

    @Override
    public Paper getPaperById(GetPaperCommand command) {
        PaperId paperId = command.paperId();
        return getPaperPort.findById(paperId);
    }

    @Override
    public List<Paper> getPapersByWriterId(GetWrittenPapersCommand command) {
        return getPaperPort.findByWriterId(command.writerId(), command.paginationRequest());
    }

    @Override
    public List<Paper> getPapersByDiaryId(Long diaryId) {
        return List.of();
    }

    @Override
    public List<Paper> getPapersByMemberId(Long memberId) {
        return List.of();
    }

    @Override
    public int getCountPapersByWriterId(Long writerId) {
        return 0;
    }

}
