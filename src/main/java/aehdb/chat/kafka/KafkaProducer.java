package aehdb.chat.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import aehdb.chat.message.model.dto.MessageDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaProducer {
    private static final String TOPIC = "chatList";
	private final KafkaTemplate<String, MessageDto.Request> kafkTemplate;
	public void sendMessage(MessageDto.Request message) {
//		System.out.println("send message : " + message.getMessage());
		this.kafkTemplate.send(TOPIC,message);
	}
}
