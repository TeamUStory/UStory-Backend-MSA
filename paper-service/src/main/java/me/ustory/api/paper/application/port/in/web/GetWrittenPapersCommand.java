package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.domain.MemberId;

public record GetWrittenPapersCommand(
    MemberId writerId,
    PaginationRequest paginationRequest
) {

}
