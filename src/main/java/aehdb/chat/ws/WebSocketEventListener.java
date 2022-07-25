package aehdb.chat.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/*
 * https://www.baeldung.com/spring-events
 * 스프링 4.2부터 application Listener 구현채로 할 필요없이 @EventListener 으로 등록
 */
@Component
public class WebSocketEventListener {
	private static final Logger LOGGER = LogManager.getLogger(WebSocketEventListener.class);

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		LOGGER.info("Received a new web socket connection" + event.getMessage());
		/*
		 *  [payload=byte[0], headers={simpMessageType=CONNECT_ACK, 
		 *  simpConnectMessage=GenericMessage [payload=byte[0],
		 *  headers={simpMessageType=CONNECT, 
		 *  stompCommand=CONNECT, 
		 *  nativeHeaders={accept-version=[1.0,1.1,1.2], 
		 *  heart-beat=[10000,10000]}, 
		 *  simpSessionAttributes={}, 
		 *  simpHeartbeat=[J@2b285848, simpSessionId=303526ac-c827-2adb-48cf-39b52ef55823}], 
		 *  simpHeartbeat=[J@2c833a1d, simpSessionId=303526ac-c827-2adb-48cf-39b52ef55823}]
		 */
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		LOGGER.info("User Disconnected : " + event.getSessionId() + "test" + event.getMessage());
//		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//		String username = (String) headerAccessor.getSessionAttributes().get("username");
//		if (username != null) {
//			logger.info("User Disconnected : " + username);
//
//			ChatMessage chatMessage = new ChatMessage();
//			chatMessage.setType(MessageType.LEAVE);
//			chatMessage.setSender(username);
//
//			messagingTemplate.convertAndSend("/topic/public", chatMessage);
//		}
	}
}