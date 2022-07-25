package aehdb.chat.message.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import aehdb.chat.message.model.dto.MessageDto;
import lombok.RequiredArgsConstructor;

/*
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-message-flow
 * 메시지 흐름 처리
 * */

@RequiredArgsConstructor
@RestController
public class ChatController {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final String MESSAGE_DESTINATION = "/sub/chat/room/";
	private final WebSocketMessageBrokerStats webSocketMessageBrokerStats;


	@MessageMapping("/chat/enter")
	public void enter(MessageDto messageDto) {
		messageDto.setMessage(messageDto.getSenderNm()+"님이 입장하셨습니다.");
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION+messageDto.getRoom().getRoomUuid(), messageDto);
	}

	@MessageMapping("/chat/message")
	public void message(MessageDto messageDto) {
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION+messageDto.getRoom().getRoomUuid(), messageDto);
	}

	@MessageMapping("/chat/exit")
	public void exit(MessageDto messageDto) {
		messageDto.setMessage(messageDto.getSenderNm()+"님이 퇴장하셨습니다.");
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION+messageDto.getRoom().getRoomUuid(), messageDto);
	}
	
	@GetMapping("/ws/webSocketMessageBrokerStats")
    public String connectedEquipments() {
        return webSocketMessageBrokerStats.toString();
    }
}

