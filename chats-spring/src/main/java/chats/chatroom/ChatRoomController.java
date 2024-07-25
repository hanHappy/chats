package chats.chatroom;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/chatrooms")
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();

        log.info("[GET] getAllChatRooms : {}", chatRooms);

        return ResponseEntity.ok(chatRooms);
    }

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom) {

        log.info("[POST] ▶▶▶ chatRoom : {}", chatRoom);

        ChatRoom newChatRoom = chatRoomService.createChatRoom(chatRoom);

        log.info("[POST] ◀◀◀ chatRoom : {}", chatRoom);

        return ResponseEntity.status(HttpStatus.CREATED).body(newChatRoom);
    }
}
