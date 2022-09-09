package aehdb.chatbot.controller;


import aehdb.chatbot.model.dto.BoardRequestDto;
import aehdb.chatbot.model.dto.ChatbotConversationRequestDto;
import aehdb.chatbot.model.dto.ChatbotOptionRequestDto;
import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.chatbot.model.entity.ChatbotOptionInfo;
import aehdb.chatbot.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping("/chatbot/conversation")
    public ChatbotConversation save(@RequestBody final ChatbotConversationRequestDto params)
    {
        return chatbotService.save(params);
    }

    @PostMapping("/chatbot/option")
    public ChatbotOptionInfo save(@RequestBody final ChatbotOptionRequestDto params)
    {
        return chatbotService.save(params);
    }


}
