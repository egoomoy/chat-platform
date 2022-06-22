package aehdb.chat.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@SuppressWarnings("rawtypes")
@Component
public class WebSocketEventListener implements ApplicationListener {
	private static final Logger LOGGER = LogManager.getLogger(WebSocketEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
	}

	@EventListener(SessionConnectEvent.class)
	public void handleWebsocketConnectListner(SessionConnectEvent event) {
		LOGGER.info("Received a new web socket connection : ");
	}

	@EventListener(SessionDisconnectEvent.class)
	public void handleWebsocketDisconnectListner(SessionDisconnectEvent event) {
		LOGGER.info("session closed : " + event.getSessionId());
	}

}