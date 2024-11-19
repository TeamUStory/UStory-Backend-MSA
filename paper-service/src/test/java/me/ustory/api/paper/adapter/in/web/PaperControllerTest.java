package me.ustory.api.paper.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.application.port.in.GetPaperCommand;
import me.ustory.api.paper.application.port.in.GetPaperUseCase;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        GetPaperCommand command = new GetPaperCommand(PaperId.of(paperId));
        Paper paper = createPaperResponse();

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

        // verify
        verify(getPaperUseCase).getPaperById(command);
    }

    // Paper 객체를 생성하는 테스트용 메서드
    private Paper createPaperResponse() {
        PaperBasicInfo basicInfo = getBasicInfo();

        PaperDetail detail = getDetail();

        return getPaper(basicInfo, detail);
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

    private Paper getPaper(PaperBasicInfo basicInfo, PaperDetail detail) {
        return Paper.builder()
            .paperId(PaperId.of(1L))
            .paperBasicInfo(basicInfo)
            .paperDetail(detail)
            .writer(MemberInfo.of(1L))
            .diary(DiaryInfo.of(
                1L,
                "다이어리 이름",
                "https://www.diary-image.gif",
                "#FFFFFF",
                "https://www.marker-image.png",
                "개인"
            ))
            .locked(false)
            .build();
    }
}