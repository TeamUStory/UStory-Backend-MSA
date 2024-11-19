package me.ustory.api.paper.application.service;

import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.application.port.in.GetPaperCommand;
import me.ustory.api.paper.application.port.in.GetWrittenPapersCommand;
import me.ustory.api.paper.application.port.out.GetPaperPort;
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

        Paper paper = getPaper(1L);

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
        Long writerId = 1L;
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, LocalDateTime.of(2024, 11, 11, 10, 0));
        GetWrittenPapersCommand command = new GetWrittenPapersCommand(writerId, paginationRequest);
        List<Paper> papers = List.of(getPaper(writerId), getPaper(writerId));

        given(getPaperPort.findByWriterId(writerId, paginationRequest)).willReturn(papers);

        // when
        List<Paper> getPapers = getPaperService.getPapersByWriterId(command);

        // then
        assertThat(getPapers)
            .hasSize(papers.size())
            .allSatisfy(paper -> assertThat(paper.getWriter().getId()).isEqualTo(writerId));

        verify(getPaperPort).findByWriterId(any(Long.class), any(PaginationRequest.class));
    }

    private Paper getPaper(Long writerId) {
        PaperBasicInfo paperBasicInfo = getPaperBasicInfo();
        PaperDetail paperDetail = getPaperDetail();
        return Paper.builder()
            .paperBasicInfo(paperBasicInfo)
            .paperDetail(paperDetail)
            .writer(MemberInfo.of(writerId))
            .diary(DiaryInfo.of(
                1L,
                "다이어리이름",
                "https://www.다이어리이미지.gif",
                "#000000",
                "https://www.마크업이미지.png",
                "개인"))
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

}