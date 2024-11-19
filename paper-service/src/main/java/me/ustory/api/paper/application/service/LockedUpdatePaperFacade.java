package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.exception.server.InternalServerException;
import me.ustory.api.paper.application.port.in.UpdatePaperCommand;
import me.ustory.api.paper.application.port.in.UpdatePaperUseCase;
import me.ustory.api.paper.application.port.out.PaperConcurrencyLockPort;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LockedUpdatePaperFacade implements UpdatePaperUseCase {

    private final PaperConcurrencyLockPort paperConcurrencyLockPort;
    private final UpdatePaperService updatePaperService;

    @Override
    public PaperId updatePaper(UpdatePaperCommand command) {
        long startTime = System.currentTimeMillis();
        long timeout = 5000;
        int retryInterval = 100;
        int maxRetryInterval = 1000;

        while (!paperConcurrencyLockPort.lock(command.paperId())) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new InternalServerException("Timeout while trying to acquire lock for paperId: " + command.paperId());
            }

            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted while acquiring lock", e);
            }

            retryInterval = Math.min(maxRetryInterval, retryInterval * 2);
        }

        PaperId paperId;
        try {
            paperId = updatePaperService.updatePaper(command);
        } finally {
            paperConcurrencyLockPort.unlock(command.paperId());
        }

        return paperId;
    }
}
