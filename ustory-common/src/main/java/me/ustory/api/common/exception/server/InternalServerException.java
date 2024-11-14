package me.ustory.api.common.exception.server;

import me.ustory.api.common.exception.CustomException;
import me.ustory.api.common.exception.ErrorCode;

public class InternalServerException extends CustomException {
    public InternalServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InternalServerException(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_EXCEPTION);
    }
}
