package chats.chatroom;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        String id = UUID.randomUUID().toString();
        chatRoom.setId(id);
        return chatRoomRepository.save(chatRoom);
    }
}
