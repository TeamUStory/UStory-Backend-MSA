package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class UnsupportedMethodTypeException extends CustomException {
    public UnsupportedMethodTypeException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UnsupportedMethodTypeException(String message) {
        super(message, ErrorCode.METHOD_NOT_ALLOWED_EXCEPTION);
    }
}
