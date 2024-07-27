package chats.chatmessage;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public Page<ChatMessage> getChatHistory(String roomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").descending());
        return chatMessageRepository.findByRoomIdOrderBySentAt(roomId, pageable);
    }

    public ChatMessage createMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }
}
