package me.ustory.api.common.controller.response;

import lombok.Getter;
import me.ustory.api.common.exception.CustomException;

@Getter
public class ErrorResponse extends ApiResponse {

    private final String errorCode;

    private final String message;

    private final String detailMessage;

    public ErrorResponse(CustomException ex) {
        this.errorCode = ex.getErrorCode().getCode();
        this.message = ex.getErrorCode().getMessage();
        this.detailMessage = ex.getMessage();
    }

    public ErrorResponse(CustomException ex, String message) {
        this.errorCode = ex.getErrorCode().getCode();
        this.message = ex.getErrorCode().getMessage();
        this.detailMessage = message;
    }

}
