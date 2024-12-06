package me.ustory.api.paper.application.service;

import me.ustory.api.paper.application.port.in.web.DeletePaperCommand;
import me.ustory.api.paper.application.port.out.persistence.UpdatePaperPort;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletePaperServiceTest {

    @Mock
    private UpdatePaperPort updatePaperPort;

    @InjectMocks
    private DeletePaperService deletePaperService;

    @DisplayName("Paper를 삭제한다.")
    @Test
    void deletePaper() {
        // given
        PaperId paperId = PaperId.of(1L);
        MemberId memberId = MemberId.of(1L);

        DeletePaperCommand command = new DeletePaperCommand(paperId, memberId);

        // when
        deletePaperService.deletePaperById(command);

        // then
        verify(updatePaperPort).deletePaper(paperId);
    }
}