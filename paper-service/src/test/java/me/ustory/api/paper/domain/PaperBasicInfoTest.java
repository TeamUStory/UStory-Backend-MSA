package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PaperBasicInfoTest {

    @DisplayName("현재 날짜보다 앞서는 Basic Info를 만들 수 없다.")
    @Test
    void createPaperBasicInfoWithValidateVisitedDate() {
        // given
        LocalDate visitedDate = LocalDate.now().plusDays(1);

        // when & then
        assertThatThrownBy(() -> PaperBasicInfo.of(
            "제목",
            Image.of("https://www.example.com/썸네일이미지.png"),
            "상호명",
            visitedDate
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Visited date is after now");
    }
}