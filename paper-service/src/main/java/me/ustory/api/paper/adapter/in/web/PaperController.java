package me.ustory.api.paper.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.ustory.api.common.controller.response.ApiResponse;
import me.ustory.api.common.controller.response.SuccessResponse;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.adapter.in.web.response.CreatePaperResponse;
import me.ustory.api.paper.adapter.in.web.response.GetPaperResponse;
import me.ustory.api.paper.application.port.in.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.CreatePaperUseCase;
import me.ustory.api.paper.application.port.in.GetPaperCommand;
import me.ustory.api.paper.application.port.in.GetPaperUseCase;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/papers")
public class PaperController {

    private final CreatePaperUseCase createPaperUseCase;
    private final GetPaperUseCase getPaperUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<CreatePaperResponse>> create(
        @RequestParam(name = "userId") Long userId,
        @Valid @RequestBody CreatePaperRequest request
    ) {
        CreatePaperCommand command = CreatePaperCommand.of(request, userId);

        PaperId paperId = createPaperUseCase.createPaper(command);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(SuccessResponse.success(new CreatePaperResponse(paperId.getId())));
    }

    @GetMapping("/{paperId}")
    public ResponseEntity<ApiResponse<GetPaperResponse>> getPaper(
        @RequestParam(name = "userId") Long userId,
        @PathVariable Long paperId
    ) {
        GetPaperCommand command = new GetPaperCommand(PaperId.of(paperId));
        Paper paper = getPaperUseCase.getPaperById(command);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(GetPaperResponse.from(paper)));
    }

}

