package aehdb.chatbot.controller;


import aehdb.chatbot.model.dto.ChatbotConversationRequestDTO;
import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.chatbot.model.repository.ChatbotConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    @GetMapping("/alldata")
    public List<Optional<ResponseEntity>> getAllData() {

        List<Optional<ResponseEntity>> tmp=new ArrayList<>();
        List<Long> tmpList=chatbotConversationRepository.getAllIds();
        System.out.println("----------------------"+chatbotConversationRepository.findById(new Long(1)).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok));
        for (int i=0;i< tmpList.size();i++){
            Long xLong=new Long(i);
            System.out.println("====================data info==============="+chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok));
            System.out.println("====================type info==============="+chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok).getClass().getName());
            tmp.add(chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok));

        }
        return tmp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatbotConversationRequestDTO> getAllDetails(@PathVariable("id") Long id) {
        System.out.println("HJIHIHI");
        return chatbotConversationRepository.findById(id).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Function<ChatbotConversation, ChatbotConversationRequestDTO> mapToChatbotConversationRequestDTO = p ->ChatbotConversationRequestDTO.builder().id(p.getId()).companyid(p.getCompanyid())
            .seq(p.getSeq()).text(p.getText()).childChatbotConversation(p.getchildChatbotConversation()).build();

}

