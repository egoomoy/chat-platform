package aehdb.chat.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import aehdb.chat.message.model.dto.MessageDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaProducer {
    private static final String TOPIC = "chatList";
	private final KafkaTemplate<String, MessageDto.Response> kafkTemplate;
	
	public void sendMessage(MessageDto.Response message) {
		System.out.println("sendMessage : " + message.getMessage());

		this.kafkTemplate.send(TOPIC,message);
		
	}
	
}
