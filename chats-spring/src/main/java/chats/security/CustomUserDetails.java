package chats.security;

import chats.user.model.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * CustomUserDetails Class는 Spring Security에서 요구하는 사용자 정보를 제공하는 것이 주 목적이다. <br/> 따라서 override해야 하는
 * method들과 관련된 필드만 기본으로 포함하면 되며, App의 요구사항에 따라 유연하게 구성하면 된다. <br/> (일반적으로 username이랑 password는 자주
 * 사용하니까 필드로 작성하고, 그 밖의 메서드를 사용하지 않는다면 해당 필드를 작성하지 않고 {@code return true}인 채로 둔다)
 */
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    /**
     * 권한을 할당할 때 "ROLE_"을 붙이는 이유 :
     * <p>
     * Spring Security의 {@code @PreAuthorize} 어노테이션이나 {@code hasRole()} 메서드에서는 "ROLE_" 접두사가 자동으로
     * 처리된다. 이를 통해 권한 검사 시 접두사를 직접 입력할 필요가 없다. 또한, Spring Security 커뮤니티에서도 이 접두사를 사용하는 것이 일반적인 관례이기도
     * 하다.
     *
     * @param username 사용자명
     * @param password 비밀번호
     * @param role     권한
     */
    public CustomUserDetails(Long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
