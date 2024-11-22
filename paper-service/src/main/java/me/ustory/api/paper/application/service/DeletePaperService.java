package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.in.DeletePaperCommand;
import me.ustory.api.paper.application.port.in.DeletePaperUseCase;
import me.ustory.api.paper.application.port.out.UpdatePaperPort;
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
