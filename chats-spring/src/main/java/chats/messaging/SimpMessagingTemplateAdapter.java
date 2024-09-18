package chats.messaging;

import chats.chatmessage.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpMessagingTemplateAdapter implements MessagingTemplate {

    final private SimpMessagingTemplate template;

    @Override
    public void sendMessage(ChatMessage message) {
        String roomId = message.getRoomId();
        template.convertAndSend("/sub/chat/rooms/" + roomId, message);
    }
}
