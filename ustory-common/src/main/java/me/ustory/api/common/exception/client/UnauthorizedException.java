package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UnauthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
