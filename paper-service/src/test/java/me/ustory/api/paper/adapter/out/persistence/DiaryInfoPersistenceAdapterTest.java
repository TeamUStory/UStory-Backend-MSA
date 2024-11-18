package me.ustory.api.paper.adapter.out.persistence;

import me.ustory.api.paper.adapter.out.feign.DiaryFeignMapper;
import me.ustory.api.paper.domain.DiaryInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({DiaryInfoPersistenceAdapter.class, JpaConfig.class, DiaryFeignMapper.class})
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
        assertThat(diaryInfoEntity.getImageUrl()).isEqualTo(diaryInfo.getImage().getUrl());
        assertThat(diaryInfoEntity.getColor()).isEqualTo(diaryInfo.getColor());
        assertThat(diaryInfoEntity.getMarkerUrl()).isEqualTo(diaryInfo.getMarker().getUrl());
    }

    private DiaryInfo createDiaryInfo(Long diaryId) {
        return DiaryInfo.of(
            diaryId,
            "다이어리이름",
            "https://www.다이어리이미지.gif",
            "#000000",
            "https://www.마크업이미지.png",
            "개인"
        );
    }
}