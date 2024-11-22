package me.ustory.api.paper.application.port.out;

import me.ustory.api.paper.domain.PaperId;

public interface PaperConcurrencyLockPort {

    boolean lock(PaperId paperId);

    boolean unlock(PaperId paperId);

}
