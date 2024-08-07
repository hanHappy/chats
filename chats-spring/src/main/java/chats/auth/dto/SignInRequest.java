package chats.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInRequest {

    private String username;
    private String password;
}
