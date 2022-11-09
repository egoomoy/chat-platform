package aehdb.chatbot.service.impl;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;
import aehdb.chatbot.model.dto.ChatbotMessageDTO.Item;
import aehdb.chatbot.model.entity.ChatbotMessage;
import aehdb.chatbot.model.repository.ChatbotMasterRepository;
import aehdb.chatbot.service.ChatbotMasterService;
import aehdb.comm.model.mapper.ChatbotMessageMapperImpl;
import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatbotMasterServiceImpl implements ChatbotMasterService {

	private final ChatbotMasterRepository chatbotMasterRepository;
	private final ChatbotMessageMapperImpl chatbotMessageMapperImpl;

	public ChatbotMessageDTO.Item getChatbotMessage(Long id) throws Exception {
		ChatbotMessage messageEntity = chatbotMasterRepository.findChatbotMessageById(id);
		ChatbotMessageDTO.Item messageDtoItem = chatbotMessageMapperImpl.entitiytoItem(messageEntity,
				new CycleAvoidingMappingContext());
		return messageDtoItem;
	}

	public Item updateChatbotMessage(@Valid ChatbotMessageDTO.Request cmdReq) throws Exception {
		ChatbotMessage botMessage = chatbotMasterRepository.findById(cmdReq.getId()).orElse(null);
		botMessage.setMessage(cmdReq.getMessage());
		botMessage.setOptnNm(cmdReq.getOptnNm());
		botMessage = chatbotMasterRepository.save(botMessage);

		return chatbotMessageMapperImpl.entitiytoItem(botMessage, new CycleAvoidingMappingContext());
	}

	public Item createChatbotMessage(@Valid ChatbotMessageDTO.Request cmdReq) {
		ChatbotMessageDTO.Item botMsDtoItm = new Item();
		botMsDtoItm.setParentId(cmdReq.getParentId());
		botMsDtoItm.setMessage(cmdReq.getMessage());
		botMsDtoItm.setOptnNm(cmdReq.getOptnNm());

		ChatbotMessage botMessage = chatbotMessageMapperImpl.itemtoEntity(botMsDtoItm,
				new CycleAvoidingMappingContext());

		botMessage = chatbotMasterRepository.save(botMessage);

		return chatbotMessageMapperImpl.entitiytoItem(botMessage, new CycleAvoidingMappingContext());

	}
}
