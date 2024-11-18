package me.ustory.api.paper.application.port.in;

import me.ustory.api.paper.domain.Paper;

public interface GetPaperUseCase {
    Paper getPaperById(GetPaperCommand command);
}
