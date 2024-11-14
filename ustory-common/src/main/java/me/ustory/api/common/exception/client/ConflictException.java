package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ConflictException(String message) {
        super(message, ErrorCode.CONFLICT_EXCEPTION);
    }
}
