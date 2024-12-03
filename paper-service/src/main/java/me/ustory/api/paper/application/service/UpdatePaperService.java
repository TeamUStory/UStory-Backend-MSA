package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.exception.client.ForbiddenException;
import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;
import me.ustory.api.paper.application.port.in.kafka.UnlockPaperCommand;
import me.ustory.api.paper.application.port.in.kafka.UnlockPaperUseCase;
import me.ustory.api.paper.application.port.in.web.UpdatePaperCommand;
import me.ustory.api.paper.application.port.out.persistence.GetPaperPort;
import me.ustory.api.paper.application.port.out.kafka.SendUnlockPaperNotificationPort;
import me.ustory.api.paper.application.port.out.persistence.UpdatePaperPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UpdatePaperService implements UnlockPaperUseCase {

    private final GetPaperPort getPaperPort;
    private final UpdatePaperPort updatePaperPort;
    private final SendUnlockPaperNotificationPort sendUnlockPaperNotificationPort;

    public PaperId updatePaper(UpdatePaperCommand command) {
        Paper paper = getPaperPort.findById(command.paperId());

        if (!paper.getDiary().getMemberInfo().isContains(command.updaterId())) {
            throw new ForbiddenException("해당 다이어리의 페이퍼 수정 권한이 없습니다.");
        }

        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            command.title(),
            command.thumbnail(),
            command.store(),
            command.visitedAt()
        );

        Address address = Address.of(command.city(), command.coordinateX(), command.coordinateY());

        PaperDetail paperDetail = PaperDetail.of(command.images(), address);

        paper.changeBasicInfo(paperBasicInfo);
        paper.changeDetail(paperDetail);

        return updatePaperPort.updatePaper(paper);
    }

    @Override
    public void unlockPaper(UnlockPaperCommand command) {
        Paper paper = getPaperPort.findById(command.paperId());

        if (paper.isCanUnlock(command.commentCount())) {
            paper.unLock();
            updatePaperPort.updatePaper(paper);

            List<MemberId> memberIds = paper.getDiary().getMemberInfo().getMemberIds();

            sendUnlockPaperNotificationPort.sendUnlockPaperNotification(paper.getPaperId(), memberIds);
        }
    }
}
