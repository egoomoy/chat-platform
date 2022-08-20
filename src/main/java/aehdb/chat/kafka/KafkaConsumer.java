package aehdb.chat.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.chat.message.service.MessageService;
import aehdb.comm.model.mapper.UpgradeMessageMapperImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class KafkaConsumer {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final String MESSAGE_DESTINATION = "/chat/sub/room/";
	
	@KafkaListener(topics = "chatList", groupId = "chatGroup")
	public void consume(MessageDto.Request message) throws IOException {
//		System.out.println("receive message : " + message.getMessage());
		simpMessagingTemplate.convertAndSend(MESSAGE_DESTINATION + message.getRoomUuid(), message);

	}

}
