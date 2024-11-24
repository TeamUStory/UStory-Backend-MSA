package me.ustory.api.comment.application.port.in;

import me.ustory.api.comment.domain.PaperId;

public record GetCommentsCommand(
    PaperId paperId
) {
    public static GetCommentsCommand of(Long paperId) {
        return new GetCommentsCommand(PaperId.of(paperId));
    }
}
