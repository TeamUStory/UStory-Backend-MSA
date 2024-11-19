package me.ustory.api.paper.application.port.out;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;

import java.util.List;

public interface GetPaperPort {

    Paper findById(PaperId id);
    List<Paper> findByWriterId(Long writerId, PaginationRequest paginationRequest);

}
