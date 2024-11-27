package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiaryInfoTest {

    @DisplayName("개인 다이어리인지 확인한다.")
    @Test
    void isIndividual() {
        // given
        MemberInfo individualMemberInfo = MemberInfo.of(List.of(MemberId.of(1L)));
        MemberInfo memberInfo = MemberInfo.of(List.of(MemberId.of(1L), MemberId.of(2L)));

        DiaryInfo individualDiaryInfo = DiaryInfo.of(
            DiaryId.of(1L),
            individualMemberInfo,
            "다이어리이름",
            "https://www.example.com/다이어리이미지.png",
            "#00000",
            "https://www.example.com/마크업이미지.png"
        );
        DiaryInfo diaryInfo = DiaryInfo.of(
            DiaryId.of(1L),
            memberInfo,
            "다이어리이름",
            "https://www.example.com/다이어리이미지.png",
            "#00000",
            "https://www.example.com/마크업이미지.png"
        );

        // when & then
        assertThat(individualDiaryInfo.isIndividualDiary()).isTrue();
        assertThat(diaryInfo.isIndividualDiary()).isFalse();
    }

    @DisplayName("다이러이에 속한 사용자 수가 같은지 확인한다.")
    @Test
    void isSameMemberCount() {
        // given
        MemberInfo memberInfo = MemberInfo.of(List.of(MemberId.of(1L), MemberId.of(2L)));

        DiaryInfo diaryInfo = DiaryInfo.of(DiaryId.of(1L),
            memberInfo,
            "다이어리이름",
            "https://www.example.com/다이어리이미지.png",
            "#00000",
            "https://www.example.com/마크업이미지.png"
        );

        // when & then
        assertThat(diaryInfo.isSameMemberCount(2)).isTrue();
        assertThat(diaryInfo.isSameMemberCount(1)).isFalse();
    }

}