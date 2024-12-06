package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.PaperId;

public record DeletePaperCommand(
    PaperId paperId,
    MemberId memberId
) {

}
