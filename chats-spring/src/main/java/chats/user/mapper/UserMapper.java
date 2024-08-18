package chats.user.mapper;

import chats.user.dto.UserDTO;
import chats.user.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
