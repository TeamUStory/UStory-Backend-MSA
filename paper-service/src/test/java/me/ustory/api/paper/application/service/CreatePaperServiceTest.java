package me.ustory.api.paper.application.service;

import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.application.port.out.CreateDiaryPort;
import me.ustory.api.paper.application.port.out.CreatePaperPort;
import me.ustory.api.paper.application.port.out.GetDiaryFeignPort;
import me.ustory.api.paper.domain.DiaryInfo;
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
        String title = "제목";
        String thumbnailImage = "https://www.대표이미지.gif";
        List<String> images = List.of("https://www.이미지1.gif", "https://www.이미지2.gif");
        LocalDate visitedDate = LocalDate.of(2020, 1, 1);
        Long writerId = 1L;
        Long diaryId = 1L;
        String city = "도로주소";
        String store = "가게명";
        Double coordinateX = 37.5494;
        Double coordinateY = 126.9169;

        CreatePaperCommand expectedCommand = new CreatePaperCommand(
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

        given(getDiaryFeignPort.getDiaryById(1L)).willReturn(DiaryInfo.of(
            1L,
            "다이어리이름",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png")
        );

        // when
        createPaperService.createPaper(expectedCommand);

        // then
        // MemberInfo 불러오기 로직이 호출되었는지 검증
        verify(getDiaryFeignPort).getDiaryById(diaryId);

        verify(createPaperPort).createPaper(any(Paper.class));
        verify(createDiaryPort).createDiary(any(DiaryInfo.class));
    }

}
