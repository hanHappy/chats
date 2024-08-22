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
@RequestMapping("/api/chat/rooms/{roomId}/messages")
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping
    public ResponseEntity<Page<ChatMessage>> getChatMessages(
        @PathVariable String roomId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {

        return ResponseEntity.ok(chatMessageService.getChatHistory(roomId, page, size));
    }

}
