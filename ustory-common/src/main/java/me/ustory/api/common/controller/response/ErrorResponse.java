package me.ustory.api.common.controller.response;

import me.ustory.api.common.exception.CustomException;

public record ErrorResponse(
    String errorCode,
    String message,
    String detailMessage
) implements ApiResponse<Void> {

    public static ErrorResponse of(CustomException ex) {
        return new ErrorResponse(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage(), ex.getMessage());
    }

    public static ErrorResponse of(CustomException ex, String customMessage) {
        return new ErrorResponse(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage(), customMessage);
    }

}
