package chats.exception;

import chats.constants.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException e) {
        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(
        UsernameNotFoundException e) {
        ErrorCode errorCode = ErrorCode.USER_NOT_FOUND;

        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
