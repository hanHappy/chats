package chats.auth.dto;

import lombok.Getter;

@Getter
public class SignInRequest {

    private String username;
    private String password;
}
