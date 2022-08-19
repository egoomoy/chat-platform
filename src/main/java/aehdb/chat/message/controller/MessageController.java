package aehdb.chat.message.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import aehdb.chat.kafka.KafkaProducer;
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
	private final KafkaProducer kafkaProducer;

	@MessageMapping("/message") // /chat/pub/message
	public void enter(MessageDto.Request req) throws Exception {
		MessageDto.Item transDto = messageSerivce.insertMessage(req);
		MessageDto.Response res = upgradeMessageMapperImpl.itemToRes(transDto);
		res.setRoomUuid(transDto.getRoom().getRoomUuid());
		kafkaProducer.sendMessage(res);

		//simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION + req.getRoomUuid(), res);
	}

	@GetMapping("/chat/messages/{id}")
	public ResponseMap getMessageDtos(@PathVariable("id") Long id) throws Exception {
		// 카프카의 보관주기를 이용하여, 데이터 베이스를 읽지 않고 바로 컨슘해서 가져올 수 있지 않을까?
		List<MessageDto.Item> MessageItemList = messageSerivce.selectMessageList(id);
		List<MessageDto.Response> responseMessageList = upgradeMessageMapperImpl.itemToRes(MessageItemList);
		return new ResponseMap(200, "", responseMessageList);
	}

	@GetMapping("/ws/webSocketMessageBrokerStats")
	public String connectedEquipments() {
		return webSocketMessageBrokerStats.toString();
	}
}

