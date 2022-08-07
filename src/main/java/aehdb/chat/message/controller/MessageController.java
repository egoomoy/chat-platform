package aehdb.chat.message.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.chat.message.service.MessageService;
import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.model.mapper.UpgradeMessageMapperImpl;
import lombok.RequiredArgsConstructor;

/*
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-message-flow
 * 메시지 흐름 처리
 * */

@RequiredArgsConstructor
@RestController
public class MessageController {
	private final MessageService messageSerivce;

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final String MESSAGE_DESTINATION = "/chat/sub/room/";
	private final WebSocketMessageBrokerStats webSocketMessageBrokerStats;
	private final UpgradeMessageMapperImpl upgradeMessageMapperImpl;

	@MessageMapping("/message") // /chat/pub/message
	public void enter(MessageDto.Request req) throws Exception {
		switch (req.getType()) {
		case ENTER:
			req.setMessage(req.getSenderNm() + "님이 입장하셨습니다.");
			break;
		case EXIT:
			req.setMessage(req.getSenderNm() + "님이 퇴장하셨습니다.");
			break;
		default:
			break;
		}

		MessageDto.Item transDto = messageSerivce.insertMessage(req);
		MessageDto.Response res = upgradeMessageMapperImpl.itemToRes(transDto);

		simpMessagingTemplate.convertAndSend(
				MESSAGE_DESTINATION + res.builder().roomUuid(transDto.getRoom().getRoomUuid()).build().getRoomUuid(),
				res);
	}

	@GetMapping("/chat/messages/{id}")
	public ResponseMap getMessageDtos(@PathVariable("id") Long id) throws Exception {
		List<MessageDto.Item> MessageItemList = messageSerivce.selectMessageList(id);
		List<MessageDto.Response> responseMessageList = upgradeMessageMapperImpl.itemToRes(MessageItemList);
		return new ResponseMap(200, "", responseMessageList);
	}

	@GetMapping("/ws/webSocketMessageBrokerStats")
	public String connectedEquipments() {
		return webSocketMessageBrokerStats.toString();
	}
}
