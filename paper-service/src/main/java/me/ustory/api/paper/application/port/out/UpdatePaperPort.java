package me.ustory.api.paper.application.port.out;

import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;

public interface UpdatePaperPort {

    PaperId updatePaper(Paper paper);

}
