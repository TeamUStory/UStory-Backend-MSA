package me.ustory.api.paper.application.service;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.application.port.in.GetDiaryPapersCommand;
import me.ustory.api.paper.application.port.in.GetMemberPapersCommand;
import me.ustory.api.paper.application.port.in.GetPaperCommand;
import me.ustory.api.paper.application.port.in.GetWrittenPapersCommand;
import me.ustory.api.paper.application.port.in.GetWrittenPapersCountCommand;
import me.ustory.api.paper.application.port.out.GetPaperPort;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetPaperServiceTest {

    @Mock
    private GetPaperPort getPaperPort;

    @InjectMocks
    private GetPaperService getPaperService;

    @DisplayName("PaperId에 해당하는 Paper를 불러온다.")
    @Test
    void getPaperById() {
        // given
        PaperId paperId = PaperId.of(1L);
        GetPaperCommand command = new GetPaperCommand(paperId);

        Paper paper = getPaper(MemberId.of(1L), DiaryId.of(1L));

        given(getPaperPort.findById(paperId)).willReturn(paper);

        // when
        Paper getPaper = getPaperService.getPaperById(command);

        // then
        assertThat(getPaper).isEqualTo(paper);

        verify(getPaperPort).findById(any(PaperId.class));
    }

    @DisplayName("작성한 Paper를 불러온다.")
    @Test
    void getWrittenPapers() {
        // given
        MemberId writerId = MemberId.of(1L);
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, LocalDateTime.of(2024, 11, 11, 10, 0));
        GetWrittenPapersCommand command = new GetWrittenPapersCommand(writerId, paginationRequest);
        List<Paper> papers = List.of(getPaper(writerId, DiaryId.of(1L)), getPaper(writerId, DiaryId.of(1L)));

        given(getPaperPort.findByWriterId(writerId, paginationRequest)).willReturn(papers);

        // when
        List<Paper> getPapers = getPaperService.getPapersByWriterId(command);

        // then
        assertThat(getPapers)
            .hasSize(papers.size())
            .allSatisfy(paper -> assertThat(paper.getWriter()).isEqualTo(writerId));

        verify(getPaperPort).findByWriterId(any(MemberId.class), any(PaginationRequest.class));
    }

    @DisplayName("작성한 Paper의 개수를 불러온다.")
    @Test
    void getWrittenPapersCount() {
        // given
        MemberId writerId = MemberId.of(1L);
        GetWrittenPapersCountCommand command = new GetWrittenPapersCountCommand(writerId);
        int expectedCount = 1;

        given(getPaperPort.findCountByWriterId(writerId)).willReturn(expectedCount);

        // when
        int count = getPaperService.getCountPapersByWriterId(command);

        // then
        assertThat(expectedCount).isEqualTo(count);
    }

    @DisplayName("Diary에 속한 Paper를 불러온다.")
    @Test
    void getPapersByDiaryId() {
        // given
        DiaryId diaryId = DiaryId.of(1L);
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, LocalDateTime.of(2024, 11, 11, 10, 0));
        LocalDate startDate = LocalDate.of(2024, 10, 10);
        LocalDate endDate = LocalDate.of(2024, 11, 11);

        GetDiaryPapersCommand command = new GetDiaryPapersCommand(diaryId, paginationRequest, startDate, endDate);
        List<Paper> papers = List.of(getPaper(MemberId.of(1L), diaryId), getPaper(MemberId.of(1L), diaryId));

        given(getPaperPort.findByDiaryId(diaryId, paginationRequest, startDate, endDate)).willReturn(papers);

        // when
        List<Paper> getPapers = getPaperService.getPapersByDiaryId(command);

        // then
        assertThat(getPapers)
            .hasSize(2)
            .allSatisfy(paper -> assertThat(paper.getDiary().getId()).isEqualTo(diaryId));

        verify(getPaperPort).findByDiaryId(any(DiaryId.class), any(PaginationRequest.class), any(LocalDate.class), any(LocalDate.class));
    }

    private Paper getPaper(MemberId writerId, DiaryId diaryId) {
        PaperBasicInfo paperBasicInfo = getPaperBasicInfo();
        PaperDetail paperDetail = getPaperDetail();
        return Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .writer(writerId)
            .diary(DiaryInfo.of(
                diaryId,
                MemberInfo.of(List.of(MemberId.of(1L))),
                "다이어리이름",
                "https://www.다이어리이미지.gif",
                "#000000",
                "https://www.마크업이미지.png"
            ))
            .build();
    }

    @DisplayName("Member와 연관된 Paper를 불러온다.")
    @Test
    void getPaperByMemberId() {
        // given
        MemberId memberId = MemberId.of(1L);
        GetMemberPapersCommand command = new GetMemberPapersCommand(memberId);

        given(getPaperPort.findByMemberId(memberId)).willReturn(List.of(getPaper(MemberId.of(1L), DiaryId.of(1L)), getPaper(MemberId.of(1L), DiaryId.of(1L))));

        // when
        List<Paper> papers = getPaperService.getPapersByMemberId(command);

        // then
        assertThat(papers).hasSize(2)
            .extracting(Paper::getDiary)
            .extracting(DiaryInfo::getMemberInfo)
            .extracting(MemberInfo::getMemberIds)
            .allSatisfy(memberIds ->
                assertThat(memberIds).contains(memberId)
            );

        verify(getPaperPort).findByMemberId(any(MemberId.class));
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

}