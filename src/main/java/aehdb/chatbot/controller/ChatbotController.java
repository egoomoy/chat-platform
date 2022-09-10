package aehdb.chatbot.controller;


import aehdb.chatbot.model.dto.ChatbotConversationRequestDTO;
import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.chatbot.model.repository.ChatbotConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

//@RestController
//@RequiredArgsConstructor
//public class ChatbotController {

//    private final ChatbotService chatbotService;
//
//    @PostMapping("/chatbot/conversation")
//    public ChatbotConversation save(@RequestBody final ChatbotConversationRequestDTO params)
//    {
//        return chatbotService.save(params);
//    }
//
//    @PostMapping("/chatbot/option")
//    public ChatbotOptionInfo save(@RequestBody final ChatbotOptionRequestDto params)
//    {
//
//        return chatbotService.save(params);
//    }
//}

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotConversationRepository chatbotConversationRepository;


    @GetMapping("/{id}")
    public ResponseEntity<ChatbotConversationRequestDTO> getAllDetails(@PathVariable("id") Long id) {
        System.out.println("HJIHIHI");
        return chatbotConversationRepository.findById(id).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Function<ChatbotConversation, ChatbotConversationRequestDTO> mapToChatbotConversationRequestDTO = p ->ChatbotConversationRequestDTO.builder().id(p.getId()).companyid(p.getCompanyid())
            .seq(p.getSeq()).text(p.getText()).childChatbotConversation(p.getchildChatbotConversation()).build();

}

