package me.ustory.api.paper.application.service;

import me.ustory.api.common.exception.client.ForbiddenException;
import me.ustory.api.common.kafka.CreateCommentKafkaDTO;
import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;
import me.ustory.api.paper.adapter.in.web.reqeust.UpdatePaperRequest;
import me.ustory.api.paper.application.port.in.kafka.UnlockPaperCommand;
import me.ustory.api.paper.application.port.in.web.UpdatePaperCommand;
import me.ustory.api.paper.application.port.out.persistence.GetPaperPort;
import me.ustory.api.paper.application.port.out.kafka.SendUnlockPaperNotificationPort;
import me.ustory.api.paper.application.port.out.persistence.UpdatePaperPort;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberId;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdatePaperServiceTest {

    @Mock
    private GetPaperPort getPaperPort;

    @Mock
    private UpdatePaperPort updatePaperPort;

    @Mock
    private SendUnlockPaperNotificationPort sendUnlockPaperNotificationPort;

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
        verify(updatePaperPort).updatePaper(expectedPaper);
    }

    @DisplayName("다이어리에 속하지 않은 사용자는 Paper를 업데이트할 수 없다.")
    @Test
    void updatePaperWithNotContainingMember() {
        // given
        PaperId paperId = PaperId.of(1L);
        Long userId = 3L;
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

        // when & then
        assertThatThrownBy(() -> updatePaperService.updatePaper(command))
            .isInstanceOf(ForbiddenException.class)
            .hasMessage("해당 다이어리의 페이퍼 수정 권한이 없습니다.");

        verify(getPaperPort).findById(paperId);
    }

    @DisplayName("잠금 해제 조건에 해당하면, 잠금을 해제하고 Paper를 업데이트한다.")
    @Test
    void unlockPaper() {
        // given
        CreateCommentKafkaDTO dto = new CreateCommentKafkaDTO(1L, 2);
        UnlockPaperCommand command = UnlockPaperCommand.of(dto);

        Paper paper = getPaper(PaperId.of(1L));
        given(getPaperPort.findById(command.paperId())).willReturn(paper);

        // when
        updatePaperService.unlockPaper(command);

        // then
        verify(updatePaperPort).updatePaper(any(Paper.class));
        verify(sendUnlockPaperNotificationPort).sendUnlockPaperNotification(any(UnlockPaperNotificationKafkaDTO.class));
    }

    @DisplayName("잠금 해제 조건에 해당하지 않으면, 잠금 해제 및 업데이트 로직을 수행하지 않는다.")
    @Test
    void unlockPaperWithInvalidCommentCount() {
        // given
        CreateCommentKafkaDTO dto = new CreateCommentKafkaDTO(1L, 1);
        UnlockPaperCommand command = UnlockPaperCommand.of(dto);

        Paper paper = getPaper(PaperId.of(1L));
        given(getPaperPort.findById(command.paperId())).willReturn(paper);

        // when
        updatePaperService.unlockPaper(command);

        // then
        verify(updatePaperPort, never()).updatePaper(any(Paper.class));
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
            .writer(MemberId.of(1L))
            .diary(DiaryInfo.of(
                DiaryId.of(1L),
                MemberInfo.of(List.of(MemberId.of(1L), MemberId.of(2L))),
                "다이어리이름",
                "https://www.다이어리이미지.gif",
                "#000000",
                "https://www.마크업이미지.png"
            ))
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