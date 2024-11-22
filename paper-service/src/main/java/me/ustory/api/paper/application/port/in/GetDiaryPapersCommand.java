package me.ustory.api.paper.application.port.in;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.domain.DiaryId;

import java.time.LocalDate;

public record GetDiaryPapersCommand (
    DiaryId diaryId,
    PaginationRequest paginationRequest,
    LocalDate startDate,
    LocalDate endDate
){
}
