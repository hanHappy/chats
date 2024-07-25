package chats;

import chats.chatmessage.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/enter")
    public void enter(ChatMessage message) {
        message.setContent(message.getSenderId() + "님이 채팅방에 입장!");
        template.convertAndSend("/sub/chat/rooms/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/sub/chat/rooms/" + message.getRoomId(), message);
    }

}
