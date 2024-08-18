package chats.auth;

import chats.auth.dto.AuthResponse;
import chats.auth.dto.SignInRequest;
import chats.auth.dto.SignUpRequest;
import chats.constants.ErrorCode;
import chats.exception.ServiceException;
import chats.security.JwtUtil;
import chats.user.model.User;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signinRequest) {
        /*
        AuthenticationManager가 authenticate를 호출할 때
        UserDetailsService의 loadUserByUsername이 호출된다.
         */
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),
                    signinRequest.getPassword())
            );

            SecurityContextHolder.getContext()
                                 .setAuthentication(authentication);

            Map<String, Object> claims = new HashMap<>();
            claims.put("username", signinRequest.getUsername());
            String token = jwtUtil.generateToken(claims);

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            // 아이디 또는 비밀번호가 틀린 경우
            throw new ServiceException(ErrorCode.INVALID_CREDENTIALS);
        } catch (AuthenticationException e) {
            // 기타 인증 관련 예외
            throw new ServiceException(ErrorCode.UNAUTHORIZED);
        }
    }

    /**
     * 회원 가입 리소스를 생성하면 201 Created 상태 코드와 함께 생성된 리소스의 URI를 응답 헤더의 Location 필드에 포함한다.
     *
     * @param signUpRequest 회원 가입 요청 파라미터
     * @return 가입된 회원
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {

        User registeredUser = userService.signUp(signUpRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                  .path("/api/users/{id}")
                                                  .buildAndExpand(registeredUser.getId())
                                                  .toUri();

        return ResponseEntity.created(location)
                             .body(registeredUser);
    }

}


