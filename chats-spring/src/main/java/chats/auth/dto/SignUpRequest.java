package chats.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = "password")
public class SignUpRequest {

    private String username;
    private String password;
}
