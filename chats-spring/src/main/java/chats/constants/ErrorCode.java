package chats.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 인증
    UNAUTHORIZED("AUTH_001", HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다"),
    FORBIDDEN("AUTH_002", HttpStatus.FORBIDDEN, "권한이 없습니다"),
    ;

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
