package aehdb.chat.chat.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import aehdb.chat.chat.model.dto.MessageDto;

import org.springframework.messaging.simp.SimpMessagingTemplate;


@RequiredArgsConstructor
@Controller
public class ChatController {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final String MESSAGE_DESTINATION = "/sub/chat/room/";
	
	@MessageMapping("/chat/enter")
	public void enter(MessageDto messageDto) {
		messageDto.setMessage(messageDto.getSenderNm()+"님이 입장하셨습니다.");
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION+messageDto.getRoomDto().getRoomUuid(), messageDto);
	}

	@MessageMapping("/chat/message")
	public void message(MessageDto messageDto) {
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION+messageDto.getRoomDto().getRoomUuid(), messageDto);
	}

	@MessageMapping("/chat/exit")
	public void exit(MessageDto messageDto) {
		// 
		messageDto.setMessage(messageDto.getSenderNm()+"님이 퇴장하셨습니다.");
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION+messageDto.getRoomDto().getRoomUuid(), messageDto);
	}

}

