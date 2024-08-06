package chats.user;

import chats.auth.dto.SignUpRequest;
import chats.constants.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        User user = User.builder()
                        .username(signUpRequest.getUsername())
                        .password(encodedPassword)
                        .role(UserRole.ROLE_USER)
                        .build();

        return userRepository.save(user);
    }
}
