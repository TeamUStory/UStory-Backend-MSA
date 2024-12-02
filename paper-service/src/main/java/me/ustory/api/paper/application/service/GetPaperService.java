package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.in.web.GetDiaryPapersCommand;
import me.ustory.api.paper.application.port.in.web.GetMemberPapersCommand;
import me.ustory.api.paper.application.port.in.web.GetPaperCommand;
import me.ustory.api.paper.application.port.in.web.GetPaperUseCase;
import me.ustory.api.paper.application.port.in.web.GetWrittenPapersCommand;
import me.ustory.api.paper.application.port.in.web.GetWrittenPapersCountCommand;
import me.ustory.api.paper.application.port.out.persistence.GetPaperPort;
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
    public int getCountPapersByWriterId(GetWrittenPapersCountCommand command) {
        return getPaperPort.findCountByWriterId(command.writerId());
    }

    @Override
    public List<Paper> getPapersByDiaryId(GetDiaryPapersCommand command) {
        return getPaperPort.findByDiaryId(command.diaryId(), command.paginationRequest(), command.startDate(), command.endDate());
    }

    @Override
    public List<Paper> getPapersByMemberId(GetMemberPapersCommand command) {
        return getPaperPort.findByMemberId(command.memberId());
    }

}
