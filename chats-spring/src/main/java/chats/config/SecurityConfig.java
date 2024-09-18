package chats.config;

import chats.filter.JwtAuthenticationFilter;
import chats.security.handler.JwtAccessDeniedHandler;
import chats.security.handler.JwtAuthenticationEntryPoint;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * {@code EnableWebSecurity} <br/> {@code Configuration} 클래스에 추가하여 {@code WebSecurityConfigurer}에
 * 정의된 Spring Security 설정을 사용한다. 즉 {@code SecurityFilterChain} bean으로 등록한다. <br/> <br/>
 * {@code @EnableMethodSecurity} <br/> 메서드 수준의 보안을 활성화한다. {@code @PreAuthorize},
 * {@code @PostAuthorize}, {@code @PreFilter}, {@code PostFilter} 를 제공하여 메서드 호출 전, 후에 보안 검사를 수행할 수
 * 있다.
 *
 * @see <a
 * href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/EnableWebSecurity.html">EnableWebSecurity</a>
 * @see <a
 * href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/method/configuration/EnableMethodSecurity.html">EnableMethodSecurity</a>
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/ws/**")
                .permitAll()
                // .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated()
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * FIXME
     *  배포 시 요청 url 수정
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        // CORS 프리플라이트 요청의 결과를 캐시하는 시간을 설정
        // 프리플라이트 요청의 결과를 브라우저가 얼마나 오래 캐시할지 지정
        // 성능 향상: 캐시 기간 동안 추가 프리플라이트 요청을 하지 않아 네트워크 부하 감소
        // 응답 시간 개선: 캐시된 정보를 사용해 빠르게 요청 처리 가능
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * 사용자 인증 처리를 위한 핵심 Interface 사용자 이름과 비밀번호를 확인하고 인증 토큰 생성
     *
     * @return AuthenticationManager 인증 매니저
     */
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
