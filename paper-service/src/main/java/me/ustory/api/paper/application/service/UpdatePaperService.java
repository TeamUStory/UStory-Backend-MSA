package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.in.UpdatePaperCommand;
import me.ustory.api.paper.application.port.out.PaperConcurrencyLockPort;
import me.ustory.api.paper.application.port.out.GetPaperPort;
import me.ustory.api.paper.application.port.out.UpdatePaperPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UpdatePaperService {

    private final GetPaperPort getPaperPort;
    private final UpdatePaperPort updatePaperPort;

    public PaperId updatePaper(UpdatePaperCommand command) {
        Paper paper = getPaperPort.findById(command.paperId());

        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            command.title(),
            Image.of(command.thumbnailImageUrl()),
            command.store(),
            command.visitedAt()
        );

        Images images = Images.of(command.imageUrls());

        Address address = Address.of(command.city(), command.coordinateX(), command.coordinateY());

        PaperDetail paperDetail = PaperDetail.of(images, address);

        paper.changeBasicInfo(paperBasicInfo);
        paper.changeDetail(paperDetail);

        return updatePaperPort.updatePaper(paper);
    }

}
