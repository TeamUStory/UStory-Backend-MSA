package me.ustory.api.common.controller.response;

public record SuccessResponse<S>(S data) implements ApiResponse<S> {

    public static ApiResponse<?> success() {
        return new SuccessResponse<>("요청이 정상적으로 처리되었습니다.");
    }

    public static <S> ApiResponse<S> success(S data) {
        return new SuccessResponse<>(data);
    }

}
