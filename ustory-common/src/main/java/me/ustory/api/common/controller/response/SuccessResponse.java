package me.ustory.api.common.controller.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> extends ApiResponse {

    private final T data;

    public static ApiResponse success() {
        return new SuccessResponse<>("요청이 정상적으로 처리되었습니다.");
    }

    public static <T> ApiResponse success(T data) {
        return new SuccessResponse<>(data);
    }

}
