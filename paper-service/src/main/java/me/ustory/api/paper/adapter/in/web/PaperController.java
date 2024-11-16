package me.ustory.api.paper.adapter.in.web;

import jakarta.validation.Valid;
import me.ustory.api.common.controller.response.ApiResponse;
import me.ustory.api.common.controller.response.SuccessResponse;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.adapter.in.web.response.CreatePaperResponse;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberInfo;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    private final CreatePaperUseCase createPaperUseCase;

    public PaperController(CreatePaperUseCase createPaperUseCase) {
        this.createPaperUseCase = createPaperUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreatePaperResponse>> create(
        @RequestParam(name = "userId") Long userId,
        @Valid @RequestBody CreatePaperRequest request
    ) {
        CreatePaperCommand command = CreatePaperCommand.builder()
            .title(request.title())
            .thumbnailImageUrl(request.thumbnailImageUrl())
            .visitedAt(request.visitedAt())
            .writerId(userId)
            .diaryId(request.diaryId())
            .imageUrls(request.imageUrls())
            .city(request.city())
            .store(request.store())
            .coordinateX(request.coordinateX())
            .coordinateY(request.coordinateY())
            .build();

        PaperId paperId = createPaperUseCase.createPaper(command);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(SuccessResponse.success(new CreatePaperResponse(1L)));
    }

}

