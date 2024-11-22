package me.ustory.api.paper.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.adapter.in.web.reqeust.UpdatePaperRequest;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.application.port.in.GetDiaryPapersCommand;
import me.ustory.api.paper.application.port.in.GetPaperCommand;
import me.ustory.api.paper.application.port.in.GetPaperUseCase;
import me.ustory.api.paper.application.port.in.GetWrittenPapersCommand;
import me.ustory.api.paper.application.port.in.UpdatePaperCommand;
import me.ustory.api.paper.application.port.in.UpdatePaperUseCase;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaperController.class)
class PaperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreatePaperUseCase createPaperUseCase;

    @MockBean
    private GetPaperUseCase getPaperUseCase;

    @MockBean
    private UpdatePaperUseCase updatePaperUseCase;

    @DisplayName("Paper를 생성한다.")
    @Test
    void createPaper() throws Exception {
        // given
        String title = "제목";
        String thumbnailImage = "https://www.대표이미지.gif";
        List<String> images = List.of("https://www.이미지1.gif", "https://www.이미지2.gif");
        LocalDate visitedDate = LocalDate.of(2020, 1, 1);
        Long writerId = 1L;
        Long diaryId = 1L;
        String city = "도로주소";
        String store = "가게명";
        Double coordinateX = 37.5494;
        Double coordinateY = 126.9169;

        CreatePaperRequest request = CreatePaperRequest.builder()
            .title(title)
            .thumbnailImageUrl(thumbnailImage)
            .imageUrls(images)
            .visitedAt(visitedDate)
            .diaryId(diaryId)
            .city(city)
            .store(store)
            .coordinateX(coordinateX)
            .coordinateY(coordinateY)
            .build();

        CreatePaperCommand command = CreatePaperCommand.of(request, writerId);

        given(createPaperUseCase.createPaper(eq(command))).willReturn(PaperId.of(1L));

        // when & then
        mockMvc.perform(post("/api/papers")
                .param("userId", writerId.toString())
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.paperId").value(1));

        then(createPaperUseCase).should()
            .createPaper(eq(command));
    }

    @DisplayName("Paper를 ID로 조회한다.")
    @Test
    void getPaperById() throws Exception {
        // given
        Long userId = 1L;
        Long paperId = 1L;
        Long diaryId = 1L;

        GetPaperCommand command = new GetPaperCommand(PaperId.of(paperId));
        Paper paper = getPaper(paperId, diaryId);

        given(getPaperUseCase.getPaperById(command)).willReturn(paper);

        // when & then
        mockMvc.perform(get("/api/papers/{paperId}", paperId)
                .param("userId", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.title").value(paper.getBasicInfo().getTitle()))
            .andExpect(jsonPath("$.data.thumbnailImageUrl").value(paper.getBasicInfo().getThumbnailImage().getUrl()))
            .andExpect(jsonPath("$.data.imageUrls[0]").value(paper.getDetail().getImages().getImagesUrl().get(0)))
            .andExpect(jsonPath("$.data.imageUrls[1]").value(paper.getDetail().getImages().getImagesUrl().get(1)))
            .andExpect(jsonPath("$.data.visitedAt").value(paper.getBasicInfo().getVisitedAt().toString()))
            .andExpect(jsonPath("$.data.store").value(paper.getBasicInfo().getStore()))
            .andExpect(jsonPath("$.data.city").value(paper.getDetail().getAddress().getCity()))
            .andExpect(jsonPath("$.data.coordinateX").value(paper.getDetail().getAddress().getCoordinateXValue()))
            .andExpect(jsonPath("$.data.coordinateY").value(paper.getDetail().getAddress().getCoordinateYValue()))
            .andExpect(jsonPath("$.data.isLocked").value(paper.isLocked()))
            .andExpect(jsonPath("$.data.diaryName").value(paper.getDiary().getName()));

        verify(getPaperUseCase).getPaperById(command);
    }

    @DisplayName("Paper를 수정한다.")
    @Test
    void updatePaper() throws Exception {
        // given
        Long paperId = 1L;
        Long userId = 1L;

        String title = "제목";
        String thumbnailImage = "https://www.대표이미지.gif";
        List<String> images = List.of("https://www.이미지1.gif", "https://www.이미지2.gif");
        LocalDate visitedDate = LocalDate.of(2020, 1, 1);
        String city = "도로주소";
        String store = "가게명";
        Double coordinateX = 37.5494;
        Double coordinateY = 126.9169;

        UpdatePaperRequest request = UpdatePaperRequest.builder()
            .title(title)
            .thumbnailImageUrl(thumbnailImage)
            .imageUrls(images)
            .visitedAt(visitedDate)
            .city(city)
            .store(store)
            .coordinateX(coordinateX)
            .coordinateY(coordinateY)
            .build();

        UpdatePaperCommand command = UpdatePaperCommand.of(request, PaperId.of(paperId), userId);

        given(updatePaperUseCase.updatePaper(command)).willReturn(PaperId.of(paperId));

        // when & then
        mockMvc.perform(put("/api/papers/{paperId}", paperId)
                .param("userId", String.valueOf(userId))
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.paperId").value(paperId));

        verify(updatePaperUseCase).updatePaper(command);
    }

    @DisplayName("작성한 Paper를 조회한다.")
    @Test
    void getWrittenPaperByMemberId() throws Exception {
        // given
        Long userId = 1L;
        Long diaryId = 1L;
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, LocalDateTime.of(2024, 11, 10, 10, 0));

        GetWrittenPapersCommand command = new GetWrittenPapersCommand(MemberId.of(userId), paginationRequest);
        List<Paper> papers = List.of(getPaper(1L, diaryId), getPaper(2L, diaryId));

        given(getPaperUseCase.getPapersByWriterId(command)).willReturn(papers);

        // when & then
        mockMvc.perform(get("/api/papers/written")
                .param("userId", String.valueOf(userId))
                .param("page", String.valueOf(paginationRequest.page()))
                .param("size", String.valueOf(paginationRequest.size()))
                .param("requestTime", paginationRequest.requestTime().toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].paperId").value(papers.get(0).getPaperId().getId()))
            .andExpect(jsonPath("$.data[0].title").value(papers.get(0).getTitle()))
            .andExpect(jsonPath("$.data[0].thumbnailImageUrl").value(papers.get(0).getThumbnailUrl()))
            .andExpect(jsonPath("$.data[0].store").value(papers.get(0).getStore()))
            .andExpect(jsonPath("$.data[0].diaryName").value(papers.get(0).getDiary().getName()))
            .andExpect(jsonPath("$.data[1].paperId").value(papers.get(1).getPaperId().getId()))
            .andExpect(jsonPath("$.data[1].title").value(papers.get(1).getTitle()))
            .andExpect(jsonPath("$.data[1].thumbnailImageUrl").value(papers.get(1).getThumbnailUrl()))
            .andExpect(jsonPath("$.data[1].store").value(papers.get(1).getStore()))
            .andExpect(jsonPath("$.data[1].diaryName").value(papers.get(1).getDiary().getName()));

        // verify
        verify(getPaperUseCase).getPapersByWriterId(command);
    }

    @DisplayName("Diary에 속한 Paper를 조회한다.")
    @Test
    void getPaperByDiaryId() throws Exception {
        // given
        Long diaryId = 1L;
        PaginationRequest paginationRequest = new PaginationRequest(1, 20, LocalDateTime.of(2024, 11, 10, 10, 0));
        LocalDate startDate = null;
        LocalDate endDate = null;

        GetDiaryPapersCommand command = new GetDiaryPapersCommand(DiaryId.of(diaryId), paginationRequest, startDate, endDate);
        List<Paper> papers = List.of(getPaper(1L, diaryId), getPaper(2L, diaryId));

        given(getPaperUseCase.getPapersByDiaryId(command)).willReturn(papers);

        // when & then
        mockMvc.perform(get("/api/papers/diary/{diaryId}", diaryId)
                .param("page", String.valueOf(paginationRequest.page()))
                .param("size", String.valueOf(paginationRequest.size()))
                .param("requestTime", paginationRequest.requestTime().toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].paperId").value(papers.get(0).getPaperId().getId()))
            .andExpect(jsonPath("$.data[0].title").value(papers.get(0).getTitle()))
            .andExpect(jsonPath("$.data[0].thumbnailImageUrl").value(papers.get(0).getThumbnailUrl()))
            .andExpect(jsonPath("$.data[0].store").value(papers.get(0).getStore()))
            .andExpect(jsonPath("$.data[0].diaryName").value(papers.get(0).getDiary().getName()))
            .andExpect(jsonPath("$.data[1].paperId").value(papers.get(1).getPaperId().getId()))
            .andExpect(jsonPath("$.data[1].title").value(papers.get(1).getTitle()))
            .andExpect(jsonPath("$.data[1].thumbnailImageUrl").value(papers.get(1).getThumbnailUrl()))
            .andExpect(jsonPath("$.data[1].store").value(papers.get(1).getStore()))
            .andExpect(jsonPath("$.data[1].diaryName").value(papers.get(1).getDiary().getName()));

        // verify
        verify(getPaperUseCase).getPapersByDiaryId(command);
    }

    private Paper getPaper(Long paperId, Long diaryId) {
        PaperDetail detail = getDetail();
        PaperBasicInfo basicInfo = getBasicInfo();
        return Paper.builder()
            .paperId(PaperId.of(paperId))
            .paperBasicInfo(basicInfo)
            .paperDetail(detail)
            .writer(MemberId.of(1L))
            .diary(DiaryInfo.of(
                DiaryId.of(diaryId),
                MemberInfo.of(List.of(MemberId.of(1L))),
                "다이어리 이름",
                "https://www.diary-image.gif",
                "#FFFFFF",
                "https://www.marker-image.png"
            ))
            .locked(false)
            .build();
    }

    private PaperBasicInfo getBasicInfo() {
        return PaperBasicInfo.of(
            "제목",
            Image.of("https://www.thumbnail-image.png"),
            "가게명",
            LocalDate.of(2024, 11, 1)
        );
    }

    private PaperDetail getDetail() {
        return PaperDetail.of(
            Images.of(List.of("https://www.image1.gif", "https://www.image2.gif")),
            Address.of("서울시", 37.5494, 126.9169)
        );
    }
}