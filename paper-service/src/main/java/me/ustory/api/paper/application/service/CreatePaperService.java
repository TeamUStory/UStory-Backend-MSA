package me.ustory.api.paper.application.service;

import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.stereotype.Service;

@Service
class CreatePaperService implements CreatePaperUseCase {

    private final CreatePaperPort createPaperPort;

    public CreatePaperService(CreatePaperPort createPaperPort) {
        this.createPaperPort = createPaperPort;
    }

    @Override
    public PaperId createPaper(CreatePaperCommand command) {
//        MemberInfo 불러오기
//        DiaryInfo 불러오기

        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            command.title(),
            Image.of(command.thumbnailImageUrl()),
            command.store(),
            command.visitedAt()
        );

        Images images = Images.of(command.imageUrls());

        Address address = Address.of(command.city(), command.coordinateX(), command.coordinateY());

        PaperDetail paperDetail = PaperDetail.of(images, address);

        Paper paper = Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            // MemberInfo 넣기
            // DiaryInfo 넣기
            .build();

        return createPaperPort.createPaper(paper);
    }

}
