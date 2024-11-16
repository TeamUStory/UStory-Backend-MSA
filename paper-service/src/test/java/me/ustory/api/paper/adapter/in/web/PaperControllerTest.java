package me.ustory.api.paper.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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

        CreatePaperCommand command = new CreatePaperCommand(
            title,
            thumbnailImage,
            visitedDate,
            writerId,
            diaryId,
            images,
            city,
            store,
            coordinateX,
            coordinateY
        );

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
}