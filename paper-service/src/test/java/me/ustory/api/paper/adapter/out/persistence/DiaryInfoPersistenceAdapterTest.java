package me.ustory.api.paper.adapter.out.persistence;

import me.ustory.api.paper.adapter.out.feign.DiaryFeignMapper;
import me.ustory.api.paper.adapter.out.persistence.config.JpaConfig;
import me.ustory.api.paper.adapter.out.persistence.entity.DiaryInfoEntity;
import me.ustory.api.paper.adapter.out.persistence.entity.MembersInfoEntity;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Members;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({DiaryInfoPersistenceAdapter.class, JpaConfig.class, DiaryFeignMapper.class})
@ActiveProfiles("test")
class DiaryInfoPersistenceAdapterTest {

    @Autowired
    private DiaryInfoPersistenceAdapter diaryInfoPersistenceAdapter;

    @Autowired
    private DiaryInfoJpaRepository diaryInfoJpaRepository;

    @DisplayName("Diary Info를 저장한다.")
    @Test
    void createDiaryInfo() {
        // given
        Long diaryId = 1L;
        DiaryInfo diaryInfo = createDiaryInfo(diaryId);

        // when
        diaryInfoPersistenceAdapter.createDiary(diaryInfo);

        // then
        DiaryInfoEntity diaryInfoEntity = diaryInfoJpaRepository.findById(diaryId).orElseThrow();

        assertThat(diaryInfoEntity.getName()).isEqualTo(diaryInfo.getName());
        assertThat(diaryInfoEntity.getImage()).isEqualTo(diaryInfo.getImage().getUrl());
        assertThat(diaryInfoEntity.getColor()).isEqualTo(diaryInfo.getColor().getValue());
        assertThat(diaryInfoEntity.getMarker()).isEqualTo(diaryInfo.getMarker().getUrl());
    }

    @DisplayName("저장된 Diary인지 확인한다.")
    @Test
    void isExistDiary() {
        // given
        Long diaryId = 1L;

        DiaryInfoEntity diaryInfoEntity = DiaryInfoEntity.of(
            diaryId,
            MembersInfoEntity.of(List.of(1L, 2L)),
            "다이어리명",
            "https://www.example.com/다이어리이미지.png",
            "#000000",
            "https://www.example.com/마커이미지.png"
        );

        diaryInfoJpaRepository.save(diaryInfoEntity);

        // when
        boolean result = diaryInfoPersistenceAdapter.isExistDiary(DiaryId.of(diaryId));
        boolean invalidResult = diaryInfoPersistenceAdapter.isExistDiary(DiaryId.of(2L));

        // then
        assertThat(result).isTrue();
        assertThat(invalidResult).isFalse();
    }

    @DisplayName("Diary Info를 업데이트한다.")
    @Test
    void updateDiaryInfo() {
        // given
        Long diaryId = 1L;
        DiaryInfoEntity diaryInfoEntity = DiaryInfoEntity.of(
            diaryId,
            MembersInfoEntity.of(List.of(1L, 2L)),
            "다이어리명",
            "https://www.example.com/다이어리이미지.png",
            "#000000",
            "https://www.example.com/마커이미지.png"
        );

        diaryInfoJpaRepository.save(diaryInfoEntity);

        DiaryInfo expectedDiaryInfo = DiaryInfo.of(
            DiaryId.of(diaryId),
            Members.of(List.of(MemberId.of(1L), MemberId.of(2L), MemberId.of(3L))),
            "다이어리명 변경",
            "https://www.example.com/다이어리이미지변경.png",
            "#000000",
            "https://www.example.com/마커이미지변경.png"
        );

        // when
        diaryInfoPersistenceAdapter.updateDiary(expectedDiaryInfo);

        // then
        DiaryInfoEntity updatedDiaryInfoEntity = diaryInfoJpaRepository.findById(diaryId).orElseThrow();

        assertThat(updatedDiaryInfoEntity.getName()).isEqualTo(expectedDiaryInfo.getName());
        assertThat(updatedDiaryInfoEntity.getImage()).isEqualTo(expectedDiaryInfo.getImage().getUrl());
        assertThat(updatedDiaryInfoEntity.getColor()).isEqualTo(expectedDiaryInfo.getColor().getValue());
        assertThat(updatedDiaryInfoEntity.getMarker()).isEqualTo(expectedDiaryInfo.getMarker().getUrl());
    }

    private DiaryInfo createDiaryInfo(Long diaryId) {
        return DiaryInfo.of(
            DiaryId.of(diaryId),
            Members.of(List.of(MemberId.of(1L))),
            "다이어리이름",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png"
        );
    }
}