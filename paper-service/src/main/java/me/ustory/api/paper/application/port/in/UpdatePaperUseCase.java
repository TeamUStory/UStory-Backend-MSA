package me.ustory.api.paper.application.port.in;

import me.ustory.api.paper.domain.PaperId;

public interface UpdatePaperUseCase {

    PaperId updatePaper(UpdatePaperCommand command);

}
