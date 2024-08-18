package chats.auth;

import chats.auth.dto.SignUpRequest;
import chats.constants.ErrorCode;
import chats.exception.ServiceException;
import chats.user.UserRepository;
import chats.user.model.User;
import chats.user.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(SignUpRequest signUpRequest) {
        userRepository.findByUsername(signUpRequest.getUsername())
                      .ifPresent(user -> {
                          throw new ServiceException(ErrorCode.DUPLICATE_USERNAME);
                      });

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        User user = User.builder()
                        .username(signUpRequest.getUsername())
                        .password(encodedPassword)
                        .role(UserRole.ROLE_USER)
                        .build();

        return userRepository.save(user);
    }
}
