package chats.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagingTemplateFactory {

    private final SimpMessagingTemplateAdapter simpTemplateAdapter;
    private final RabbitTemplateAdapter rabbitTemplateAdapter;

    public MessagingTemplate getTempate(MessagingTemplateType type) {
        switch (type) {
            case SIMP_TEMPLATE -> {
                return simpTemplateAdapter;
            }
            case RABBIT_TEMPLATE -> {
                return rabbitTemplateAdapter;
            }
        }
        return simpTemplateAdapter;
    }
}
