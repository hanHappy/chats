package chats.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * {@code @EnableWebSocketMessageBroker} <br/> WebSocket Message Broker를 활성화하는 데 사용되며, STOMP 프로토콜을
 * 사용한 메시징을 지원한다. <br/> 메시지를 라우팅하고 관리하는 중앙 컴포넌트를 구성하며, 클라이언트가 연결할 End-point를 정의한다.
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 메시지 브로커 구성 <br/><br/> {@code config.enableSimpleBroker("/pub")} : "/pub"으로 시작하는 목적지로 보내진
     * 메시지를 처리한다. 예를 들어, 클라이언트가 "/pub/coffees"를 구독하면, 이 주제로 보내진 메시지를 받게 된다. <br/><br/>
     * {@code config.setApplicationDestinationPrefixes("/sub")} : 클라이언트에서 서버로 메시지를 보낼 때 사용할 접두사를
     * 지정한다. 이 접두사로 시작하는 메시지는 {@code @MessageMapping}이 붙은 메서드로 라우팅된다. 예를 들어, 클라이언트가 "/sub/coffee"로
     * 메시지를 보내면 서버의 {@code MessageMapping("/hello")} 메서드가 이를 처리한다.
     *
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/pub");
        config.enableSimpleBroker("/sub");
    }

    /**
     * STOMP End-point 등록 <br/><br/> {@code registry.addEndpoint("/ws")} : "/ws" 경로에 WebSocket
     * End-point를 등록한다. 클라이언트는 이 End-point를 통해 WebSocket 연결을 시작한다. <br/><br/>
     * {@code registry.withSockJS()} : WebSocket을 지원하지 않는 브라우저를 위한 대체 옵션을 제공한다. (IE9 이하 버전은
     * WebSocket을 지원하지 않는다) WebSocket, Long Polling 등을 사용하여 실시간 통신을 Emulate한다.
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:5173")
                .withSockJS();
    }

}
