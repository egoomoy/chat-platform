package aehdb.chatbot.service.impl;

import org.springframework.stereotype.Service;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;
import aehdb.chatbot.model.entity.ChatbotMessage;
import aehdb.chatbot.model.repository.ChatbotMasterRepository;
import aehdb.chatbot.service.ChatbotMasterService;
import aehdb.comm.model.mapper.ChatbotMessageMapperImpl;
import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatbotMasterServiceImpl implements ChatbotMasterService{

	private final ChatbotMasterRepository chatbotMasterRepository;
	private final ChatbotMessageMapperImpl chatbotMessageMapperImpl;
	
	public ChatbotMessageDTO.Item getChatbotMessage(Long id) throws Exception {
		ChatbotMessage messageEntity = chatbotMasterRepository.findChatbotMessageById(id);
		ChatbotMessageDTO.Item messageDtoItem = chatbotMessageMapperImpl.entitiytoItem(messageEntity, new CycleAvoidingMappingContext());
		return messageDtoItem;
	}
}
