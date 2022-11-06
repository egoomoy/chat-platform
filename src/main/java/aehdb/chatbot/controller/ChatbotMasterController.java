package aehdb.chatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;
import aehdb.chatbot.service.ChatbotMasterService;
import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.model.mapper.ChatbotMessageMapperImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatbotMasterController {
	private final ChatbotMasterService chatbotMasterService;
	private final ChatbotMessageMapperImpl cmmi;
	@GetMapping("/botMessage/{id}")
	public ResponseMap getChatbotData(@PathVariable("id") Long id) throws Exception {
		
		ChatbotMessageDTO.Item ChatbotMessageList = chatbotMasterService.getChatbotMessage(id);
		ChatbotMessageDTO.Response resChatbotMessageList = cmmi.itemToRes(ChatbotMessageList);
		
		return new ResponseMap(200, "", resChatbotMessageList);
	}

   
}
