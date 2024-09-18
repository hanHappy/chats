package chats.messaging;

import chats.chatmessage.ChatMessage;

public interface MessagingTemplate {

    void sendMessage(ChatMessage message);

}
