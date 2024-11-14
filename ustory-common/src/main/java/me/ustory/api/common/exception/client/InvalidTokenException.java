package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException(String message) {
        super(message, ErrorCode.INVALID_TOKEN_EXCEPTION);
    }
}
