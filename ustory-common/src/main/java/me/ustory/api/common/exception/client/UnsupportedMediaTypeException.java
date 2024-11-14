package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class UnsupportedMediaTypeException extends CustomException {
    public UnsupportedMediaTypeException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UnsupportedMediaTypeException(String message) {
        super(message, ErrorCode.UNSUPPORTED_MEDIA_TYPE_EXCEPTION);
    }
}
