package me.ustory.api.paper.application.service;

import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.out.CreateDiaryPort;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.application.port.out.GetDiaryFeignPort;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.Paper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

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

    @InjectMocks
    private CreatePaperService createPaperService;

    @DisplayName("Paper를 생성한다.")
    @Test
    void createPaper() {
        // given
        Long diaryId = 1L;

        CreatePaperCommand expectedCommand = createPaperCommand(diaryId);

        DiaryInfo diaryInfo = createDiaryInfo(diaryId);

        given(getDiaryFeignPort.getDiaryById(1L)).willReturn(diaryInfo);
        given(createDiaryPort.createDiary(any(DiaryInfo.class))).willReturn(diaryInfo);

        // when
        createPaperService.createPaper(expectedCommand);

        // then
        // MemberInfo 불러오기 로직이 호출되었는지 검증
        verify(getDiaryFeignPort).getDiaryById(diaryId);

        verify(createDiaryPort).createDiary(any(DiaryInfo.class));
        verify(createPaperPort).createPaper(any(Paper.class));
    }

    private CreatePaperCommand createPaperCommand(Long diaryId) {
        String title = "제목";
        String thumbnailImage = "https://www.대표이미지.gif";
        List<String> images = List.of("https://www.이미지1.gif", "https://www.이미지2.gif");
        LocalDate visitedDate = LocalDate.of(2020, 1, 1);
        Long writerId = 1L;
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

    private DiaryInfo createDiaryInfo(Long diaryId) {
        return DiaryInfo.of(
            DiaryId.of(diaryId),
            MemberInfo.of(List.of(MemberId.of(1L))),
            "다이어리이름",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png"
        );
    }

}
