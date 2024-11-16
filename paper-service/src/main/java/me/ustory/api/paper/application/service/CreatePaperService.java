package me.ustory.api.paper.application.service;

import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.Paper;
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

        Paper paper = Paper.builder()
            .title(command.title())
            .thumbnailImageUrl(command.thumbnailImageUrl())
            .store(command.store())
            .visitedAt(command.visitedAt())
            // MemberInfo 넣기
            // DiaryInfo 넣기
            .build();

        Images images = Images.of(command.imageUrls());

        Address address = Address.builder()
            .city(command.city())
            .coordinateX(command.coordinateX())
            .coordinateY(command.coordinateY())
            .build();

        PaperDetail paperDetail = PaperDetail.builder()
            .images(images)
            .address(address)
            .build();

        return createPaperPort.createPaper(paper, paperDetail);
    }

}
