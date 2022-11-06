package aehdb.chatbot.service;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;

public interface ChatbotMasterService {
	
	ChatbotMessageDTO.Item getChatbotMessage(Long id) throws Exception;


}
