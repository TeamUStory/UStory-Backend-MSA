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
        DiaryInfo diaryInfo = createDiaryInfo(List.of(MemberId.of(1L)));

        // when
        Paper paper = createPaper(diaryInfo);

        // then
        assertThat(paper.isLocked()).isFalse();
    }

    @DisplayName("개인 다이어리를 제외한 다이어리의 Paper를 생성한 경우, 잠금을 건다.")
    @Test
    void createPaperWhenNotIndividual() {
        // given
        DiaryInfo diaryInfo = createDiaryInfo(List.of(MemberId.of(1L), MemberId.of(2L), MemberId.of(3L)));

        // when
        Paper paper = createPaper(diaryInfo);

        // then
        assertThat(paper.isLocked()).isTrue();
    }

    @DisplayName("Paper의 잠금을 해제할 수 있다.")
    @Test
    void unlock() {
        // given
        DiaryInfo diaryInfo = createDiaryInfo(List.of(MemberId.of(1L), MemberId.of(2L), MemberId.of(3L)));
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
            .writer(MemberId.of(1L))
            .build();
    }

    private DiaryInfo createDiaryInfo(List<MemberId> memberIds) {
        return DiaryInfo.of(
            DiaryId.of(1L),
            MemberInfo.of(memberIds),
            "다이어리명",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png"
        );
    }

}
