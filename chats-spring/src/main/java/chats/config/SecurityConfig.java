package chats.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * {@code EnableWebSecurity} <br/>
 * {@code Configuration} 클래스에 추가하여 {@code WebSecurityConfigurer}에 정의된 Spring Security 설정을 사용한다.
 * 즉 {@code SecurityFilterChain} bean으로 등록한다. <br/>
 * <br/>
 * {@code @EnableMethodSecurity} <br/>
 * 메서드 수준의 보안을 활성화한다. {@code @PreAuthorize}, {@code @PostAuthorize}, {@code @PreFilter}, {@code
 * PostFilter} 를 제공하여 메서드 호출 전, 후에 보안 검사를 수행할 수 있다.
 *
 * @see <a
 * href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/EnableWebSecurity.html">EnableWebSecurity</a>
 * @see <a
 * href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/method/configuration/EnableMethodSecurity.html">EnableMethodSecurity</a>
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


}
