package me.ustory.api.paper.adapter.out.persistence;

import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({PaperPersistenceAdapter.class, JpaConfig.class, PaperMapper.class, PaperDetailMapper.class})
class PaperPersistenceAdapterTest {

    @Autowired
    private PaperPersistenceAdapter paperPersistenceAdapter;

    @Autowired
    private PaperJpaRepository paperJpaRepository;

    @Autowired
    private PaperDetailJpaRepository paperDetailJpaRepository;

    @DisplayName("Paper를 생성한다.")
    @Test
    void createPaper() {
        // given
        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            "제목",
            Image.of("https://www.대표이미지.png"),
            "가게명",
            LocalDate.of(2024, 10, 1)
        );

        PaperDetail paperDetail = PaperDetail.of(
            Images.of(List.of("https://www.이미지1.gif", "https://www.이미지2.gif")),
            Address.of("도로주소", 37.5494, 126.9169)
        );

        Paper paper = Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .writer(MemberInfo.of(1L))
            .diary(DiaryInfo.of(
                1L,
                "다이어리이름",
                "https://www.다이어리이미지.gif",
                "#000000",
                "https://www.마크업이미지.png"))
            .build();

        // when
        PaperId savedPaperId = paperPersistenceAdapter.createPaper(paper);

        // then
        PaperEntity savedPaper = paperJpaRepository.findById(savedPaperId.getId()).orElseThrow();
        assertThat(savedPaper.getTitle()).isEqualTo(paper.getTitle());
        assertThat(savedPaper.getThumbnailImageUrl()).isEqualTo(paper.getThumbnailUrl());
        assertThat(savedPaper.getStore()).isEqualTo(paper.getStore());
        assertThat(savedPaper.getVisitedAt()).isEqualTo(paper.getVisitedDate());

        PaperDetailEntity savedPaperDetail = paperDetailJpaRepository.findById(savedPaperId.getId()).orElseThrow();
        assertThat(savedPaperDetail.getAddress().getCity()).isEqualTo(paperDetail.getAddress().getCity());
        assertThat(savedPaperDetail.getAddress().getCoordinateX()).isEqualTo(paperDetail.getAddress().getCoordinateXValue());
        assertThat(savedPaperDetail.getAddress().getCoordinateY()).isEqualTo(paperDetail.getAddress().getCoordinateYValue());
        assertThat(savedPaperDetail.getImages().getImageUrls()).isEqualTo(paperDetail.getImages().getImagesUrl());

    }
}
