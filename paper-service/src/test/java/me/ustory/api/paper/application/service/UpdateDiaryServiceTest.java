package me.ustory.api.paper.application.service;

import me.ustory.api.paper.application.port.in.kafka.UpdateDiaryCommand;
import me.ustory.api.paper.application.port.out.persistence.GetDiaryPort;
import me.ustory.api.paper.application.port.out.persistence.UpdateDiaryPort;
import me.ustory.api.common.vo.Color;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.common.vo.Image;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Members;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateDiaryServiceTest {

    @Mock
    private GetDiaryPort getDiaryPort;

    @Mock
    private UpdateDiaryPort updateDiaryPort;

    @InjectMocks
    private UpdateDiaryService updateDiaryService;

    @DisplayName("다이어리를 업데이트한다.")
    @Test
    void updateDiary() {
        // given
        UpdateDiaryCommand command = new UpdateDiaryCommand(
            DiaryId.of(1L),
            Members.of(List.of(MemberId.of(1L), MemberId.of(2L), MemberId.of(3L))),
            "Updated Diary",
            Image.of("https://www.example.com/이미지.png"),
            Color.of("#000000"),
            Image.of("https://www.example.com/이미지2.png")
        );

        given(getDiaryPort.isExistDiary(command.diaryId())).willReturn(true);

        // when
        updateDiaryService.updateDiary(command);

        // then
        DiaryInfo expectedDiaryInfo = DiaryInfo.of(
            command.diaryId(),
            command.members(),
            command.name(),
            command.image(),
            command.color(),
            command.marker()
        );

        verify(updateDiaryPort).updateDiary(expectedDiaryInfo);
    }

    @DisplayName("다이어리가 존재하지 않는 경우, 업데이트 로직을 수행하지 않는다.")
    @Test
    void updateDiaryWhenDiaryDoesNotExist() {
        // given
        UpdateDiaryCommand command = new UpdateDiaryCommand(
            DiaryId.of(1L),
            Members.of(List.of(MemberId.of(1L), MemberId.of(2L), MemberId.of(3L))),
            "Updated Diary",
            Image.of("https://www.example.com/이미지.png"),
            Color.of("#000000"),
            Image.of("https://www.example.com/이미지2.png")
        );

        given(getDiaryPort.isExistDiary(command.diaryId())).willReturn(false);

        // when
        updateDiaryService.updateDiary(command);

        // then
        verify(updateDiaryPort, never()).updateDiary(any(DiaryInfo.class));
    }
}
