package chats.chatmessage;

import chats.messaging.MessagingTemplate;
import chats.messaging.MessagingTemplateFactory;
import chats.messaging.MessagingTemplateType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    @Value("${env}")
    private String env;

    private MessagingTemplate template;
    private final MessagingTemplateFactory templateFactory;

    private final ChatMessageService chatMessageService;

    @PostConstruct
    public void init() {
        MessagingTemplateType templateType = "local".equals(env)
            ? MessagingTemplateType.SIMP_TEMPLATE : null;

        this.template = templateFactory.getTempate(templateType);
    }

    @GetMapping("/api/chat/rooms/{roomId}/messages")
    public ResponseEntity<Page<ChatMessage>> getChatMessages(
        @PathVariable String roomId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(chatMessageService.getChatHistory(roomId, page, size));
    }

    @MessageMapping("/chat/enter")
    public void enter(ChatMessage message) {
        message.setContent(message.getSenderId() + "님이 채팅방에 입장!");
        template.sendMessage(message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.sendMessage(message);

        // 메시지 데이터 저장
        chatMessageService.createMessage(message);
    }
}
