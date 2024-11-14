package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class AccessTokenExpiredException extends CustomException {
    public AccessTokenExpiredException(String message) {
        super(message, ErrorCode.EXPIRED_TOKEN_EXCEPTION);
    }
}
