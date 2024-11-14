package me.ustory.api.common.exception.client;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class RefreshTokenExpiredException extends CustomException {
    public RefreshTokenExpiredException(String message) {
        super(message, ErrorCode.EXPIRED_REFRESH_TOKEN_EXCEPTION);
    }
}
