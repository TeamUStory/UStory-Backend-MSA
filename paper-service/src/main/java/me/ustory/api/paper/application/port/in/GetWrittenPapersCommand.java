package me.ustory.api.paper.application.port.in;

import me.ustory.api.common.controller.reqeust.PaginationRequest;

public record GetWrittenPapersCommand(
    Long writerId,
    PaginationRequest paginationRequest
) {

}
