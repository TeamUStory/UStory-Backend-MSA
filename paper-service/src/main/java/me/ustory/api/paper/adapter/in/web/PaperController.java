package me.ustory.api.paper.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.ustory.api.common.controller.reqeust.PaginationRequest;
import me.ustory.api.common.controller.response.ApiResponse;
import me.ustory.api.common.controller.response.SuccessResponse;
import me.ustory.api.paper.adapter.in.web.reqeust.CreatePaperRequest;
import me.ustory.api.paper.adapter.in.web.reqeust.UpdatePaperRequest;
import me.ustory.api.paper.adapter.in.web.response.CreatePaperResponse;
import me.ustory.api.paper.adapter.in.web.response.DeletePaperResponse;
import me.ustory.api.paper.adapter.in.web.response.GetPaperMapResponse;
import me.ustory.api.paper.adapter.in.web.response.GetPapersCountResponse;
import me.ustory.api.paper.adapter.in.web.response.GetPaperPreviewResponse;
import me.ustory.api.paper.adapter.in.web.response.GetPaperResponse;
import me.ustory.api.paper.adapter.in.web.response.UpdatePaperResponse;
import me.ustory.api.paper.application.port.in.web.CreatePaperCommand;
import me.ustory.api.paper.application.port.in.web.CreatePaperUseCase;
import me.ustory.api.paper.application.port.in.web.DeletePaperCommand;
import me.ustory.api.paper.application.port.in.web.DeletePaperUseCase;
import me.ustory.api.paper.application.port.in.web.GetDiaryPapersCommand;
import me.ustory.api.paper.application.port.in.web.GetMemberPapersCommand;
import me.ustory.api.paper.application.port.in.web.GetPaperCommand;
import me.ustory.api.paper.application.port.in.web.GetPaperUseCase;
import me.ustory.api.paper.application.port.in.web.GetWrittenPapersCommand;
import me.ustory.api.paper.application.port.in.web.GetWrittenPapersCountCommand;
import me.ustory.api.paper.application.port.in.web.UpdatePaperCommand;
import me.ustory.api.paper.application.port.in.web.UpdatePaperUseCase;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Paper;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/papers")
public class PaperController {

    private final CreatePaperUseCase createPaperUseCase;
    private final GetPaperUseCase getPaperUseCase;
    private final UpdatePaperUseCase updatePaperUseCase;
    private final DeletePaperUseCase deletePaperUseCase;

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

    @PutMapping("/{paperId}")
    public ResponseEntity<ApiResponse<UpdatePaperResponse>> update(
        @RequestParam(name = "userId") Long userId,
        @PathVariable Long paperId,
        @Valid @RequestBody UpdatePaperRequest request
    ) {
        UpdatePaperCommand command = UpdatePaperCommand.of(request, PaperId.of(paperId), MemberId.of(userId));
        PaperId updatedPaperId = updatePaperUseCase.updatePaper(command);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(new UpdatePaperResponse(updatedPaperId.getId())));
    }

    @GetMapping("/written")
    public ResponseEntity<ApiResponse<List<GetPaperPreviewResponse>>> getPapersByWriterId(
        @RequestParam(name = "userId") Long userId,
        @ModelAttribute PaginationRequest paginationRequest
    ) {
        GetWrittenPapersCommand command = new GetWrittenPapersCommand(MemberId.of(userId), paginationRequest);
        List<Paper> papers = getPaperUseCase.getPapersByWriterId(command);

        List<GetPaperPreviewResponse> response = papers.stream()
            .map(GetPaperPreviewResponse::of)
            .toList();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(response));
    }

    @GetMapping("/written/count")
    public ResponseEntity<ApiResponse<GetPapersCountResponse>> countPapersByUser(
        @RequestParam(name = "userId") Long userId
    ) {
        GetWrittenPapersCountCommand command = new GetWrittenPapersCountCommand(MemberId.of(userId));
        int count = getPaperUseCase.getCountPapersByWriterId(command);

        GetPapersCountResponse response = new GetPapersCountResponse(count);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(response));
    }

    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<ApiResponse<List<GetPaperPreviewResponse>>> getPapersByDiary(
        @PathVariable Long diaryId,
        @ModelAttribute PaginationRequest paginationRequest,
        @RequestParam(name = "startDate", required = false) LocalDate startDate,
        @RequestParam(name = "endDate", required = false) LocalDate endDate
    ) {
        GetDiaryPapersCommand command = new GetDiaryPapersCommand(DiaryId.of(diaryId), paginationRequest, startDate, endDate);

        List<Paper> papers = getPaperUseCase.getPapersByDiaryId(command);

        List<GetPaperPreviewResponse> response = papers.stream()
            .map(GetPaperPreviewResponse::of)
            .toList();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(response));
    }

    @GetMapping("/map")
    public ResponseEntity<ApiResponse<List<GetPaperMapResponse>>> getPapersByUserForMap(
        @RequestParam(name = "userId") Long userId
    ) {
        GetMemberPapersCommand command = new GetMemberPapersCommand(MemberId.of(userId));

        List<Paper> papers = getPaperUseCase.getPapersByMemberId(command);

        List<GetPaperMapResponse> response = papers.stream()
            .map(GetPaperMapResponse::from)
            .toList();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(response));
    }

    @DeleteMapping("/{paperId}")
    public ResponseEntity<ApiResponse<DeletePaperResponse>> deletePaper(
        @PathVariable Long paperId,
        @RequestParam(name = "userId") Long userId
    ) {
        DeletePaperCommand command = new DeletePaperCommand(PaperId.of(paperId), MemberId.of(userId));

        deletePaperUseCase.deletePaperById(command);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.success(new DeletePaperResponse("성공적으로 삭제되었습니다.")));
    }

}

