package me.ustory.api.paper.adapter.out.persistence;

import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.Paper;
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
@Import({PaperPersistenceAdapter.class, PaperMapper.class, PaperDetailMapper.class})
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
        Paper paper = Paper.builder()
            .title("제목")
            .thumbnailImageUrl("https://www.대표이미지.gif")
            .store("가게명")
            .visitedAt(LocalDate.of(2020, 1, 1))
            .writer(MemberInfo.of(1L))
            .diary(DiaryInfo.of(1L)).build();

        PaperDetail paperDetail = PaperDetail.builder()
            .address(Address.builder()
                .city("도로주소")
                .coordinateX(37.5494)
                .coordinateY(126.9169).build())
            .images(Images.of(
                List.of("https://www.이미지1.gif", "https://www.이미지2.gif")
            ))
            .build();

        // when
        PaperId savedPaperId = paperPersistenceAdapter.createPaper(paper, paperDetail);

        // then
        PaperEntity savedPaper = paperJpaRepository.findById(savedPaperId.getId()).orElseThrow();
        assertThat(savedPaper.getTitle()).isEqualTo(paper.getTitle());
        assertThat(savedPaper.getThumbnailImageUrl()).isEqualTo(paper.getThumbnailImageUrl());
        assertThat(savedPaper.getStore()).isEqualTo(paper.getStore());
        assertThat(savedPaper.getVisitedAt()).isEqualTo(paper.getVisitedAt());

        PaperDetailEntity savedPaperDetail = paperDetailJpaRepository.findById(savedPaperId.getId()).orElseThrow();
        assertThat(savedPaperDetail.getAddress().getCity()).isEqualTo(paperDetail.getAddress().getCity());
        assertThat(savedPaperDetail.getAddress().getCoordinateX()).isEqualTo(paperDetail.getAddress().getCoordinateX());
        assertThat(savedPaperDetail.getAddress().getCoordinateY()).isEqualTo(paperDetail.getAddress().getCoordinateY());
        assertThat(savedPaperDetail.getImages().getImageUrls()).isEqualTo(paperDetail.getImages().getImageUrls());

    }
}
