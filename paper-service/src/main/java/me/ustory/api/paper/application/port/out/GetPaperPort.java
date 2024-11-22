package me.ustory.api.paper.application.port.out;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;

import java.time.LocalDate;
import java.util.List;

public interface GetPaperPort {

    Paper findById(PaperId id);
    List<Paper> findByWriterId(MemberId writerId, PaginationRequest paginationRequest);
    List<Paper> findByDiaryId(DiaryId diaryId, PaginationRequest paginationRequest, LocalDate startDate, LocalDate endDate);

}
