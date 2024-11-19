package me.ustory.api.paper.application.service;

import me.ustory.api.paper.adapter.in.web.reqeust.UpdatePaperRequest;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.UpdatePaperCommand;
import me.ustory.api.paper.application.port.out.GetPaperPort;
import me.ustory.api.paper.application.port.out.UpdatePaperPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdatePaperServiceTest {

    @Mock
    private GetPaperPort getPaperPort;

    @Mock
    private UpdatePaperPort updatePaperPort;

    @InjectMocks
    private UpdatePaperService updatePaperService;

    @DisplayName("Paper를 업데이트한다.")
    @Test
    void updatePaper() {
        // given
        PaperId paperId = PaperId.of(1L);
        Long userId = 1L;
        UpdatePaperCommand command = createUpdatePaperCommand(paperId, userId);
        Paper expectedPaper = getPaper(paperId);
        expectedPaper.changeBasicInfo(PaperBasicInfo.of(
            "제목1",
            Image.of("https://www.대표이미지1.gif"),
            "가게명",
            LocalDate.of(2020, 1, 1)
        ));

        expectedPaper.changeDetail(PaperDetail.of(
            Images.of(List.of("https://www.이미지3.gif", "https://www.이미지4.gif")),
            Address.of("도로주소", 37.5494, 126.9169)
        ));

        given(getPaperPort.findById(paperId)).willReturn(getPaper(paperId));
        given(updatePaperPort.updatePaper(any(Paper.class))).willReturn(paperId);

        // when
        updatePaperService.updatePaper(command);

        // then
        verify(getPaperPort).findById(paperId);
        verify(updatePaperPort).updatePaper(eq(expectedPaper));
    }

    private UpdatePaperCommand createUpdatePaperCommand(PaperId paperId, Long userId) {
        UpdatePaperRequest request = new UpdatePaperRequest(
            "제목1",
            "https://www.대표이미지1.gif",
            List.of("https://www.이미지3.gif", "https://www.이미지4.gif"),
            LocalDate.of(2020, 1, 1),
            "도로주소",
            "가게명",
            37.5494,
            126.9169
        );

        return UpdatePaperCommand.of(
            request,
            paperId,
            userId
        );
    }

    private Paper getPaper(PaperId paperId) {
        return Paper.builder()
            .paperId(paperId)
            .paperBasicInfo(getPaperBasicInfo())
            .paperDetail(getPaperDetail())
            .writer(MemberInfo.of(1L))
            .diary(DiaryInfo.of(
                1L,
                "다이어리이름",
                "https://www.다이어리이미지.gif",
                "#000000",
                "https://www.마크업이미지.png",
                "개인"))
            .build();
    }

    private PaperDetail getPaperDetail() {
        return PaperDetail.of(
            Images.of(List.of("https://www.이미지1.gif", "https://www.이미지2.gif")),
            Address.of("도로주소", 37.5494, 126.9169)
        );
    }

    private PaperBasicInfo getPaperBasicInfo() {
        return PaperBasicInfo.of(
            "제목",
            Image.of("https://www.대표이미지.png"),
            "가게명",
            LocalDate.of(2024, 10, 1)
        );
    }

}