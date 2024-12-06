package me.ustory.api.paper.application.port.out.persistence;

import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;

public interface UpdatePaperPort {

    PaperId updatePaper(Paper paper);
    void deletePaper(PaperId paperId);

}
