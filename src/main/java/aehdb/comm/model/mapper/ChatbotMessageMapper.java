package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;
import aehdb.chatbot.model.entity.ChatbotMessage;

@Mapper(componentModel = "spring")
public interface ChatbotMessageMapper
		extends UpgradeGenericMapper<ChatbotMessageDTO.Item, ChatbotMessage, ChatbotMessageDTO.Request, ChatbotMessageDTO.Response> {

}
