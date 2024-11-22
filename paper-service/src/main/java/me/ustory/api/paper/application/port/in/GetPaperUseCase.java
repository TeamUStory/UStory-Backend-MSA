package me.ustory.api.paper.application.port.in;

import me.ustory.api.paper.domain.Paper;

import java.util.List;

public interface GetPaperUseCase {
    Paper getPaperById(GetPaperCommand command);
    List<Paper> getPapersByWriterId(GetWrittenPapersCommand command);
    List<Paper> getPapersByDiaryId(GetDiaryPapersCommand command);
    List<Paper> getPapersByMemberId(Long memberId);
    int getCountPapersByWriterId(Long writerId);
}
