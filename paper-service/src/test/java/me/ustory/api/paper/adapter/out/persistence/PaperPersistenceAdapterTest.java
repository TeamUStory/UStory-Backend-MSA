package me.ustory.api.paper.adapter.out.persistence;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.domain.Address;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.Images;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperBasicInfo;
import me.ustory.api.paper.domain.PaperDetail;
import me.ustory.api.paper.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({PaperPersistenceAdapter.class, JpaConfig.class, QueryDslConfig.class, PaperMapper.class, PaperDetailMapper.class})
@ActiveProfiles("test")
class PaperPersistenceAdapterTest {

    @Autowired
    private PaperPersistenceAdapter paperPersistenceAdapter;

    @Autowired
    private PaperJpaRepository paperJpaRepository;

    @Autowired
    private PaperDetailJpaRepository paperDetailJpaRepository;

    @DisplayName("Paper를 저장한다.")
    @Test
    void createPaper() {
        // given
        PaperBasicInfo paperBasicInfo = getPaperBasicInfo();
        PaperDetail paperDetail = getPaperDetail();
        Paper paper = getPaper(paperBasicInfo, paperDetail);

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

    @DisplayName("Paper를 불러온다.")
    @Test
    void getPaper() {
        // given
        PaperBasicInfo paperBasicInfo = getPaperBasicInfo();
        PaperDetail paperDetail = getPaperDetail();
        Paper paper = getPaper(paperBasicInfo, paperDetail);

        PaperEntity savedPaper = paperJpaRepository.save(PaperMapper.mapToJpaEntity(paper));
        paperDetailJpaRepository.save(PaperDetailMapper.mapToJpaEntity(savedPaper.getId(), paperDetail));


        PaperId id = PaperId.of(savedPaper.getId());

        // when
        Paper foundPaper = paperPersistenceAdapter.findById(id);

        // then
        assertThat(foundPaper.getTitle()).isEqualTo(paper.getTitle());
        assertThat(foundPaper.getStore()).isEqualTo(paper.getStore());
        assertThat(foundPaper.getVisitedDate()).isEqualTo(paper.getVisitedDate());
        assertThat(foundPaper.getThumbnailUrl()).isEqualTo(paper.getThumbnailUrl());

        assertThat(foundPaper.getDetail().getAddress()).isEqualTo(paper.getDetail().getAddress());
        assertThat(foundPaper.getDetail().getImages()).isEqualTo(paper.getDetail().getImages());

        assertThat(foundPaper.getDiary().getName()).isEqualTo(paper.getDiary().getName());
        assertThat(foundPaper.getDiary().getColor()).isEqualTo(paper.getDiary().getColor());
        assertThat(foundPaper.getDiary().getImage()).isEqualTo(paper.getDiary().getImage());
        assertThat(foundPaper.getDiary().getMarker()).isEqualTo(paper.getDiary().getMarker());
    }

    @DisplayName("Paper를 업데이트한다.")
    @Sql("PaperPersistenceAdapterTest.sql")
    @Test
    void updatePaper() {
        // given
        Paper paper = paperPersistenceAdapter.findById(PaperId.of(1L));

        PaperBasicInfo paperBasicInfo = PaperBasicInfo.of(
            "수정된 제목",
            Image.of("https://www.example.com/수정된썸네일.png")
            ,"수정된 상호명",
            LocalDate.of(2024, 10, 1)
        );
        paper.changeBasicInfo(paperBasicInfo);

        PaperDetail paperDetail = PaperDetail.of(
            Images.of(List.of("https://www.example.com/수정된이미지.png")),
            Address.of("주소", 32.123, 128.123)
        );
        paper.changeDetail(paperDetail);

        // when
        paperPersistenceAdapter.updatePaper(paper);

        // then
        Paper updatedPaper = paperPersistenceAdapter.findById(PaperId.of(1L));

        assertThat(updatedPaper.getTitle()).isEqualTo(paper.getTitle());
        assertThat(updatedPaper.getThumbnailUrl()).isEqualTo(paper.getThumbnailUrl());
        assertThat(updatedPaper.getStore()).isEqualTo(paper.getStore());
        assertThat(updatedPaper.getVisitedDate()).isEqualTo(paper.getVisitedDate());

        assertThat(updatedPaper.getDetail().getImages()).isEqualTo(paper.getDetail().getImages());
        assertThat(updatedPaper.getDetail().getAddress()).isEqualTo(paper.getDetail().getAddress());
    }

    @DisplayName("작성한 Paper를 불러온다.")
    @Sql("PaperPersistenceAdapterTest.sql")
    @Test
    void getWrittenPapers() {
        // given
        Long writerId = 1L;
        LocalDateTime requestTime = LocalDateTime.of(2024, 11, 19, 18, 0);

        PaginationRequest paginationRequest = new PaginationRequest(1, 20, requestTime);

        // when
        List<Paper> papers = paperPersistenceAdapter.findByWriterId(MemberId.of(writerId), paginationRequest);

        // then
        assertThat(papers).hasSize(3)
            .extracting(Paper::getWriter)
            .extracting(MemberId::getValue)
            .containsOnly(writerId);
    }

    @DisplayName("작성한 Paper를 불러올 때, 생성 시간이 요청 시간보다 늦는 Paper만 불러온다.")
    @Sql("PaperPersistenceAdapterTest.sql")
    @ParameterizedTest(name = "{index}: RequestTime에 따라 Paper를 불러오는 테스트 [{0}]")
    @CsvSource({
        "2024-11-18T12:00:00, 1",
        "2024-11-18T18:00:00, 2",
        "2024-11-19T18:00:00, 3"
    })
    void getWrittenPapersWhenRequestTime(LocalDateTime requestTime, int expectedSize) {
        // given
        Long writerId = 1L;
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, requestTime);

        // when
        List<Paper> papers = paperPersistenceAdapter.findByWriterId(MemberId.of(writerId), paginationRequest);

        // then
        assertThat(papers).hasSize(expectedSize)
            .extracting(Paper::getWriter)
            .extracting(MemberId::getValue)
            .containsOnly(writerId);
    }

    @DisplayName("작성한 Paper의 개수를 불러온다.")
    @Sql("PaperPersistenceAdapterTest.sql")
    @Test
    void getWrittenPapersCount() {
        // given
        Long writerId = 1L;

        // when
        int count = paperPersistenceAdapter.findCountByWriterId(MemberId.of(writerId));

        // then
        assertThat(count).isEqualTo(3);
    }

    @DisplayName("작성한 Paper가 없는 경우, 0개로 반환한다.")
    @Sql("PaperPersistenceAdapterTest.sql")
    @Test
    void getWrittenPapersCountWithoutWrittenPaper() {
        // given
        Long writerId = 2L;

        // when
        int count = paperPersistenceAdapter.findCountByWriterId(MemberId.of(writerId));

        // then
        assertThat(count).isZero();
    }

    @DisplayName("다이어리에 속한 Paper를 불러온다.")
    @Sql("PaperPersistenceAdapterTest.sql")
    @Test
    void getPaperByDiaryId() {
        // given
        DiaryId diaryId = DiaryId.of(1L);
        LocalDateTime requestTime = LocalDateTime.of(2024, 11, 19, 18, 0);
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, requestTime);

        // when
        List<Paper> papers = paperPersistenceAdapter.findByDiaryId(diaryId, paginationRequest, null, null);

        // then
        assertThat(papers).hasSize(3)
            .extracting(Paper::getDiary)
            .extracting(DiaryInfo::getId)
            .containsOnly(diaryId);
    }

    @DisplayName("다이어리에 속한 Paper를 불러올 때, 날짜 범위를 지정할 수 있다.")
    @ParameterizedTest
    @MethodSource("dateRangeProvider")
    @Sql("PaperPersistenceAdapterTest.sql")
    void getPapersWithDateRange(LocalDate startDate, LocalDate endDate, int expectedSize) {
        // given
        DiaryId diaryId = DiaryId.of(1L);
        LocalDateTime requestTime = LocalDateTime.of(2024, 11, 19, 18, 0);
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, requestTime);

        // when
        List<Paper> papers = paperPersistenceAdapter.findByDiaryId(diaryId, paginationRequest, startDate, endDate);

        // then
        if (expectedSize > 0) {
            assertThat(papers).hasSize(expectedSize)
                .extracting(Paper::getDiary)
                .extracting(DiaryInfo::getId)
                .containsOnly(diaryId);
        }
        else {
            assertThat(papers).isEmpty();
        }
    }

    private Paper getPaper(PaperBasicInfo paperBasicInfo, PaperDetail paperDetail) {
        return Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .writer(MemberId.of(1L))
            .diary(DiaryInfo.of(
                DiaryId.of(1L),
                MemberInfo.of(List.of(MemberId.of(1L))),
                "다이어리이름",
                "https://www.다이어리이미지.gif",
                "#000000",
                "https://www.마크업이미지.png"
            ))
            .build();
    }

    private PaperDetail getPaperDetail() {
        return PaperDetail.of(
            Images.of(List.of("https://www.이미지1.gif", "https://www.이미지2.gif")),
            Address.of("도로주소", 37.5494, 126.9169)
        );
    }

    private PaperBasicInfo getPaperBasicInfo() {
        return PaperBasicInfo.of(
            "제목",
            Image.of("https://www.대표이미지.png"),
            "가게명",
            LocalDate.of(2024, 10, 1)
        );
    }

    private static Stream<Arguments> dateRangeProvider() {
        return Stream.of(
            Arguments.of(LocalDate.of(2024, 11, 15), LocalDate.of(2024, 11, 18), 2),
            Arguments.of(LocalDate.of(2024, 11, 18), LocalDate.of(2024, 11, 19), 3),
            Arguments.of(LocalDate.of(2024, 11, 20), LocalDate.of(2024, 11, 21), 0)
        );
    }
}
