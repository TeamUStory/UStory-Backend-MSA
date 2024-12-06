package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.paper.domain.PaperId;

public record GetPaperCommand (
    PaperId paperId
) {
}
