package chats.chatroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.socket.WebSocketSession;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    @Id
    private String id;
    private String ownerId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;


    /**
     * JPA는 Entity를 DB에 저장할 때 객체를 직렬화해야 하는데, WebSocketSession은 JPA Entity가 아니며, 직렬화할 수 없는 객체이다.
     * <br/><br/> 따라서 일반 필드처럼 작성할 경우 오류가 발생하기 때문에 {@code @Transient} 어노테이션을 통해 JPA가 이 필드를 무시하도록
     * 설정한다.
     */
    @Transient
    private Set<WebSocketSession> sessions = new HashSet<>();

}
