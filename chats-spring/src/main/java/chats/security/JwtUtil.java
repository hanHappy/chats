package chats.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    private SecretKey secretKey;

    /**
     * 빈 초기화 시 실행된다. <br/>
     * SECRET_KEY를 바이트 배열로 변환하여 HMAC-SHA 알고리즘에 사용할 SecretKey 객체를 생성한다.
     */
    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 주어진 클레임으로 JWT 토큰을 생성한다.
     *
     * @param claims 토큰에 포함될 클레임 정보
     * @return 생성된 JWT 토큰 문자열
     */
    public String generateToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                   .claims(claims)
                   .issuedAt(new Date(now))
                   .expiration(new Date(now + 1000 * 60 * 60 * 10)) // 10시간
                   .signWith(secretKey)
                   .compact();
    }

    /**
     * 주어진 토큰의 유효성을 검증한다.
     *
     * @param token 검증할 JWT 토큰
     * @return 토큰이 유효하면 true, 그렇지 않으면 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("invalid JWT token : {}", e.getMessage());
        }
        return false;
    }

    /**
     * 주어진 토큰에서 클레임 정보를 추출한다.
     * <br/><br/>
     * 클레임이란?
     * <br/>
     * 토큰에 포함된 정보의 조각들로, 사용자 식별, 권한 정보 전달 등의 용도로 사용된다.
     *
     * @param token JWT 토큰
     * @return 토큰에 포함된 클레임 정보
     * @throws JwtException 토큰이 유효하지 않거나 파싱할 수 없는 경우
     */
    public Claims getClaims(String token) {
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }
}
