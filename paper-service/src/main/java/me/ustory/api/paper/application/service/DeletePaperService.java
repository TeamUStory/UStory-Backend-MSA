package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.in.web.DeletePaperCommand;
import me.ustory.api.paper.application.port.in.web.DeletePaperUseCase;
import me.ustory.api.paper.application.port.out.persistence.UpdatePaperPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeletePaperService implements DeletePaperUseCase {

    private final UpdatePaperPort updatePaperPort;

    @Override
    public void deletePaperById(DeletePaperCommand command) {
        updatePaperPort.deletePaper(command.paperId());
    }

}
