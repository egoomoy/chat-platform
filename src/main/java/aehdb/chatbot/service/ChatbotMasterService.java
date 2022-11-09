package aehdb.chatbot.service;

import javax.validation.Valid;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;
import aehdb.chatbot.model.dto.ChatbotMessageDTO.Item;

public interface ChatbotMasterService {
	
	ChatbotMessageDTO.Item getChatbotMessage(Long id) throws Exception;

	Item updateChatbotMessage(@Valid ChatbotMessageDTO.Request cmdReq) throws Exception;

	Item createChatbotMessage(@Valid ChatbotMessageDTO.Request cmdReq);


}
