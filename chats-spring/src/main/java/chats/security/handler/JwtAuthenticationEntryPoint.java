package chats.security.handler;

import chats.constants.ErrorCode;
import chats.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 다음의 상황에 호출된다. 1. 인증되지 않은 접근 2. JWT 토큰 만료
 *
 * @see chats.config.SecurityConfig#filterChain(HttpSecurity)
 */
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorCode.getStatus().value());

        chats.exception.ErrorResponse errorResponse = ErrorResponse.of(errorCode,
            request.getServletPath());

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
