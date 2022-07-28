package aehdb.chat.message.service;

import java.util.List;
import java.util.UUID;

import aehdb.chat.message.model.dto.MessageDto;

public interface MessageService {
	
	public MessageDto.Item insertMessage(MessageDto.Request req) throws Exception;
	
	List<MessageDto.Item> selectMessageList(Long id) throws Exception;


}
