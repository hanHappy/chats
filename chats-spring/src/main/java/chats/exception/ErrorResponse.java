package chats.exception;

import chats.constants.ErrorCode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public record ErrorResponse(
    String code,
    int status,
    String message,
    LocalDateTime time,
    List<ValidationError> errors
) {

    // 기본 에러 응답
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
            errorCode.getCode(),
            errorCode.getStatus().value(),
            errorCode.getMessage(),
            LocalDateTime.now(),
            new ArrayList<>()
        );
    }

    // BindingResult 에러 응답
    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(
            errorCode.getCode(),
            errorCode.getStatus().value(),
            errorCode.getMessage(),
            LocalDateTime.now(),
            ValidationError.of(bindingResult)
        );
    }

    // ValidationError를 위한 내부 record
    public record ValidationError(String field, String value, String reason) {

        public static List<ValidationError> of(final BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                .map(error -> new ValidationError(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()))
                .toList();
        }
    }
}
