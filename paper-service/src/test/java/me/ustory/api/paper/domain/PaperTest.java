package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaperTest {

    @DisplayName("개인 다이어리의 Paper를 생성한 경우, 잠금을 걸지 않는다.")
    @Test
    void createPaperWhenIndividual() {
        // given
        DiaryInfo diaryInfo = createDiaryInfo("개인");

        // when
        Paper paper = createPaper(diaryInfo);

        // then
        assertThat(paper.isLocked()).isFalse();
    }

    @DisplayName("개인 다이어리를 제외한 다이어리의 Paper를 생성한 경우, 잠금을 건다.")
    @Test
    void createPaperWhenNotIndividual() {
        // given
        DiaryInfo diaryInfo = createDiaryInfo("가족");

        // when
        Paper paper = createPaper(diaryInfo);

        // then
        assertThat(paper.isLocked()).isTrue();
    }

    @DisplayName("Paper의 잠금을 해제할 수 있다.")
    @Test
    void unlock() {
        // given
        DiaryInfo diaryInfo = createDiaryInfo("가족");
        Paper paper = createPaper(diaryInfo);

        // when
        paper.unLock();

        // then
        assertThat(paper.isLocked()).isFalse();
    }

    private Paper createPaper(DiaryInfo diaryInfo) {

        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            "제목",
            Image.of("https://www.대표이미지.gif"),
            "상호명",
            LocalDate.of(2024, 10, 1)
        );

        PaperDetail paperDetail = PaperDetail.of(
            Images.of(List.of("https://www.이미지1.png", "https://www.이미지2.png")),
            Address.of("주소", 43.1234, 128.1234)
        );

        return Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .diary(diaryInfo)
            .writer(MemberInfo.of(1L))
            .build();
    }

    private DiaryInfo createDiaryInfo(String category) {
        return DiaryInfo.of(
            1L,
            "다이어리이름",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png",
            category
        );
    }

}
