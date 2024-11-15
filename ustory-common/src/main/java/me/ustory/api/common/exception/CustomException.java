package me.ustory.api.common.exception;

public abstract class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    protected CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected CustomException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
