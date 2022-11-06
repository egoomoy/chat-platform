package aehdb.chatbot.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chatbot.model.dto.ChatbotConversationRequestDTO;
import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.chatbot.model.repository.ChatbotConversationRepository;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotConversationRepository chatbotConversationRepository;


    public JSONObject convertingJsonData(List<Optional<ChatbotConversationRequestDTO>> tmp) {

        Map<String,Object> jsonConvertResult=new LinkedHashMap<>();
        List<Map> optionsList=new ArrayList<>();

        for (int i=0;i<tmp.size();i++){
            Map<String,Object> jsonConvert=new LinkedHashMap<>();
            Optional<ChatbotConversationRequestDTO> oDto=tmp.get(i);
            jsonConvert.put("text",oDto.get().getText());
            if  (oDto.get().getChildChatbotConversation().size()!=0) {
                for (ChatbotConversation chatbotConversation : oDto.get().getChildChatbotConversation()) {
                    Map<String, Object> options = new LinkedHashMap<>();
                    options.put("text", chatbotConversation.getText());
                    options.put("next", chatbotConversation.getNext());
                    options.put("childId", chatbotConversation.getId());

                    optionsList.add(options);
                }
                jsonConvert.put("child",optionsList);
            }
            String s=String.valueOf(i+1);
            jsonConvertResult.put(s,jsonConvert);
        }

        JSONObject jsonObject = new JSONObject(jsonConvertResult);

        return jsonObject;
    }


    @GetMapping("/alldata")
    public JSONObject getAllData() {
        Map<String, Object> data = new HashMap<String, Object>();
        List<Optional<ChatbotConversationRequestDTO>> tmp=new ArrayList<>();
        List<Long> tmpList=chatbotConversationRepository.getAllIds();
        for (int i=0;i< tmpList.size();i++){
            Long xLong=new Long(tmpList.get(i));
            tmp.add(chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO));

        }
        JSONObject jsonObject=null;
        jsonObject = convertingJsonData(tmp);
        return jsonObject;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatbotConversationRequestDTO> getAllDetails(@PathVariable("id") Long id) {
        return chatbotConversationRepository.findById(id).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Function<ChatbotConversation, ChatbotConversationRequestDTO> mapToChatbotConversationRequestDTO = p ->ChatbotConversationRequestDTO.builder().id(p.getId()).companyid(p.getCompanyid())
            .seq(p.getSeq()).text(p.getText()).childChatbotConversation(p.getchildChatbotConversation()).build();

}

