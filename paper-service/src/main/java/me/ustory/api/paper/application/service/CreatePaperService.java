package me.ustory.api.paper.application.service;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.exception.client.ForbiddenException;
import me.ustory.api.common.kafka.CreatePaperNotificationKafkaDTO;
import me.ustory.api.paper.application.port.in.web.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.web.CreatePaperUseCase;
import me.ustory.api.paper.application.port.out.persistence.CreateDiaryPort;
import me.ustory.api.paper.application.port.out.persistence.CreatePaperPort;
import me.ustory.api.paper.application.port.out.feign.GetDiaryFeignPort;
import me.ustory.api.paper.application.port.out.kafka.SendCreatePaperNotificationPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.DiaryInfo;
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
class CreatePaperService implements CreatePaperUseCase {

    private final CreatePaperPort createPaperPort;
    private final CreateDiaryPort createDiaryPort;
    private final GetDiaryFeignPort getDiaryFeignPort;
    private final SendCreatePaperNotificationPort sendCreatePaperNotificationPort;

    @Override
    public PaperId createPaper(CreatePaperCommand command) {

        DiaryInfo diaryInfo = getDiaryFeignPort.getDiaryById(command.diaryId());

        DiaryInfo savedDiaryInfo = createDiaryPort.createDiary(diaryInfo);

        if (!savedDiaryInfo.getMemberInfo().isContains(command.writerId())) {
            throw new ForbiddenException("해당 다이어리의 페이퍼 작성 권한이 없습니다.");
        }

        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            command.title(),
            command.thumbnail(),
            command.store(),
            command.visitedAt()
        );

        Address address = Address.of(command.city(), command.coordinateX(), command.coordinateY());

        PaperDetail paperDetail = PaperDetail.of(command.imageUrls(), address);

        Paper paper = Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .diary(savedDiaryInfo)
            .build();

        PaperId savedPaperId = createPaperPort.createPaper(paper);

        List<Long> memberIds = paper.getDiary().getMemberInfo().getMemberIds().stream()
            .map(MemberId::getValue)
            .filter(memberId -> memberId.equals(command.writerId()))
            .toList();

        CreatePaperNotificationKafkaDTO notificationDto = new CreatePaperNotificationKafkaDTO(savedPaperId.getId(), memberIds);

        sendCreatePaperNotificationPort.sendCreatePaperNotification(notificationDto);

        return savedPaperId;
    }

}
