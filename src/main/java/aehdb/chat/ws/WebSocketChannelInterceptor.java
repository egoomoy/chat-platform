package aehdb.chat.ws;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;


/*
 * Stomp Command (connect/sub/disconnect/hart-beat...) 을 이용해
 * 채팅방 현황이나 전반적인 상태를 관리 확인?
 * 
 * 
 */

public class WebSocketChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        StompCommand command = accessor.getCommand();
//        String test = accessor.getDetailedLogMessage(null);
//        System.out.println(test);

        return message;
    }
}