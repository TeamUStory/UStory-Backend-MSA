package me.ustory.api.paper.adapter.out.feign;

import me.ustory.api.common.feign.DiaryFeignDTO;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DiaryFeignAdapterTest {

    @Mock
    private DiaryFeignClient diaryFeignClient;

    @InjectMocks
    private DiaryFeignAdapter diaryFeignAdapter;

    @DisplayName("diaryId로 DiaryInfo를 조회한다.")
    @Test
    void getDiaryById() {
        // given
        Long diaryId = 1L;
        DiaryFeignDTO response = createDiaryFeignResponse(diaryId);

        given(diaryFeignClient.findDiaryById(diaryId)).willReturn(response);

        // when
        DiaryInfo result = diaryFeignAdapter.getDiaryById(DiaryId.of(diaryId));

        // then
        verify(diaryFeignClient).findDiaryById(diaryId);

        assertThat(result).isNotNull();
        assertThat(result.getId().getValue()).isEqualTo(response.diaryId());
    }

    private DiaryFeignDTO createDiaryFeignResponse(Long diaryId) {
        return new DiaryFeignDTO(
            diaryId,
            List.of(1L),
            "다이어리 이름",
            "https://example.com/image.png",
            "#FFFFFF",
            "https://example.com/markup.png"
        );
    }
}