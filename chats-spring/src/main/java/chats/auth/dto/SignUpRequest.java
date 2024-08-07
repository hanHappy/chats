package chats.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpRequest {

    private String username;
    private String password;
}
