package chats.user;

import chats.constants.ErrorCode;
import chats.exception.ServiceException;
import chats.user.dto.UserDTO;
import chats.user.mapper.UserMapper;
import chats.user.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getAllUsers() {
        return null;
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(
                                      () -> new ServiceException(ErrorCode.USER_NOT_FOUND));
        return userMapper.userToUserDTO(user);
    }

}
