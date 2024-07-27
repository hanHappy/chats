package chats.chatmessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat/messages")
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping("/history/{roomId}")
    public ResponseEntity<Page<ChatMessage>> getChatHistory(
        @PathVariable String roomId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {

        log.info("[GET] getAllChatMessages : {}",
            chatMessageService.getChatHistory(roomId, page, size));

        return ResponseEntity.ok(chatMessageService.getChatHistory(roomId, page, size));
    }

}
