package me.ustory.api.paper.application.service;

import me.ustory.api.common.exception.client.ForbiddenException;
import me.ustory.api.common.kafka.CreatePaperNotificationKafkaDTO;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.out.CreateDiaryPort;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.application.port.out.GetDiaryFeignPort;
import me.ustory.api.paper.application.port.out.SendCreatePaperNotificationPort;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.Paper;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePaperServiceTest {

    @Mock
    private CreatePaperPort createPaperPort;

    @Mock
    private CreateDiaryPort createDiaryPort;

    @Mock
    private GetDiaryFeignPort getDiaryFeignPort;

    @Mock
    private SendCreatePaperNotificationPort sendCreatePaperNotificationPort;

    @InjectMocks
    private CreatePaperService createPaperService;

    @DisplayName("Paper를 생성한다.")
    @Test
    void createPaper() {
        // given
        Long diaryId = 1L;
        Long userId = 1L;
        List<MemberId> membersId = List.of(MemberId.of(1L));

        CreatePaperCommand expectedCommand = createPaperCommand(diaryId, userId);

        DiaryInfo diaryInfo = createDiaryInfo(diaryId, membersId);

        given(getDiaryFeignPort.getDiaryById(1L)).willReturn(diaryInfo);
        given(createDiaryPort.createDiary(any(DiaryInfo.class))).willReturn(diaryInfo);

        given(createPaperPort.createPaper(any(Paper.class))).willReturn(PaperId.of(1L));

        // when
        createPaperService.createPaper(expectedCommand);

        // then
        verify(getDiaryFeignPort).getDiaryById(diaryId);

        verify(createDiaryPort).createDiary(any(DiaryInfo.class));
        verify(createPaperPort).createPaper(any(Paper.class));

        verify(sendCreatePaperNotificationPort).sendCreatePaperNotification(any(CreatePaperNotificationKafkaDTO.class));
    }

    @DisplayName("다이어리에 속하지 않은 사람은 Paper를 작성할 수 없다.")
    @Test
    void createPaperWithNotContainsMember() {
        // given
        Long diaryId = 1L;
        Long userId = 2L;
        List<MemberId> membersId = List.of(MemberId.of(1L));

        CreatePaperCommand expectedCommand = createPaperCommand(diaryId, userId);

        DiaryInfo diaryInfo = createDiaryInfo(diaryId, membersId);

        given(getDiaryFeignPort.getDiaryById(1L)).willReturn(diaryInfo);
        given(createDiaryPort.createDiary(any(DiaryInfo.class))).willReturn(diaryInfo);

        // when & then
        assertThatThrownBy(() -> createPaperService.createPaper(expectedCommand))
            .isInstanceOf(ForbiddenException.class)
            .hasMessage("해당 다이어리의 페이퍼 작성 권한이 없습니다.");

        // then
        verify(getDiaryFeignPort).getDiaryById(diaryId);
    }

    private CreatePaperCommand createPaperCommand(Long diaryId, Long userId) {
        String title = "제목";
        String thumbnailImage = "https://www.대표이미지.gif";
        List<String> images = List.of("https://www.이미지1.gif", "https://www.이미지2.gif");
        LocalDate visitedDate = LocalDate.of(2020, 1, 1);
        Long writerId = userId;
        String city = "도로주소";
        String store = "가게명";
        Double coordinateX = 37.5494;
        Double coordinateY = 126.9169;

        return new CreatePaperCommand(
            title,
            thumbnailImage,
            visitedDate,
            writerId,
            diaryId,
            images,
            city,
            store,
            coordinateX,
            coordinateY
        );
    }

    private DiaryInfo createDiaryInfo(Long diaryId, List<MemberId> membersId) {
        return DiaryInfo.of(
            DiaryId.of(diaryId),
            MemberInfo.of(membersId),
            "다이어리이름",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png"
        );
    }

}
