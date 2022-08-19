package aehdb.chat.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import aehdb.chat.message.model.dto.MessageDto;

@Service
public class KafkaConsumer {
	@KafkaListener(topics = "chatList", groupId = "chatGroup")

	public void consume(MessageDto.Response message) throws IOException {
		System.out.println("receive message : " + message.getMessage());
	}

}
