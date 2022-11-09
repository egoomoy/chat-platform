package aehdb.chatbot.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chatbot.model.dto.ChatbotMessageDTO;
import aehdb.chatbot.model.dto.ChatbotMessageDTO.Response.ResponseBuilder;
import aehdb.chatbot.service.ChatbotMasterService;
import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.model.mapper.ChatbotMessageMapperImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatbotMasterController {
	private final ChatbotMasterService chatbotMasterService;
	private final ChatbotMessageMapperImpl cmmi;

	@GetMapping("/chat/botMessage/{id}")
	public ResponseMap getChatbotData(@PathVariable("id") Long id) throws Exception {

		ChatbotMessageDTO.Item ChatbotMessageList = chatbotMasterService.getChatbotMessage(id);
		ChatbotMessageDTO.Response resChatbotMessageList = cmmi.itemToRes(ChatbotMessageList);

		return new ResponseMap(200, "", resChatbotMessageList);
	}

	@PatchMapping("/mng/botMessage")
	public ResponseMap updatebotMessage(@RequestBody @Valid ChatbotMessageDTO.Request cmdReq) throws Exception {
		final ChatbotMessageDTO.Item rtnCmdItem = chatbotMasterService.updateChatbotMessage(cmdReq);

		ResponseBuilder rb = ChatbotMessageDTO.Response.builder();
		rb.id(rtnCmdItem.getId())
				.optnNm(rtnCmdItem.getOptnNm())
				.message(rtnCmdItem.getMessage())
				.parentId(rtnCmdItem.getParentId());

		ChatbotMessageDTO.Response response = rb.build();

		return new ResponseMap(200, "", response);

	}

	@PostMapping("/mng/botMessage")
	public ResponseMap createbotMessage(@RequestBody @Valid ChatbotMessageDTO.Request cmdReq) throws Exception {
		final ChatbotMessageDTO.Item rtnCmdItem = chatbotMasterService.createChatbotMessage(cmdReq);

		ResponseBuilder rb = ChatbotMessageDTO.Response.builder();
		rb.id(rtnCmdItem.getId())
				.optnNm(rtnCmdItem.getOptnNm())
				.message(rtnCmdItem.getMessage())
				.parentId(rtnCmdItem.getParentId());

		ChatbotMessageDTO.Response response = rb.build();

		return new ResponseMap(200, "", response);

	}

}
