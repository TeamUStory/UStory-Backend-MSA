package me.ustory.api.common.controller.reqeust;

import me.ustory.api.common.exception.ErrorCode;
import me.ustory.api.common.exception.client.ValidationException;

import java.time.LocalDateTime;

public record PaginationRequest(
    int page,
    int size,
    LocalDateTime requestTime
) {
    public PaginationRequest {

        if (requestTime == null) {
            throw new ValidationException("호출 시간은 필수 값입니다.", ErrorCode.MISSING_REQUIRED_PARAMETER);
        }

        if (page <= 0) {
            throw new ValidationException("페이지는 0보다 커야합니다.", ErrorCode.PARAMETER_INCORRECT_FORMAT);
        }

        if (size <= 0) {
            throw new ValidationException("사이즈는 0보다 커야합니다.", ErrorCode.PARAMETER_INCORRECT_FORMAT);
        }

    }
}
