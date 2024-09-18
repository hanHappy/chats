package chats.messaging;

import chats.chatmessage.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitTemplateAdapter implements MessagingTemplate {

    private final RabbitTemplate template;

    @Override
    public void sendMessage(ChatMessage message) {
    }
}
