package aehdb.chatbot.controller;


import aehdb.chatbot.model.dto.ChatbotConversationRequestDTO;
import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.chatbot.model.repository.ChatbotConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;


import org.json.simple.parser.JSONParser;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;



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
                    optionsList.add(options);
                }
                jsonConvert.put("options",optionsList);
            }
            String s=String.valueOf(i+1);
            jsonConvertResult.put(s,jsonConvert);
        }

        JSONObject jsonObject = new JSONObject(jsonConvertResult);
        System.out.println("=========jsonObject==========="+jsonObject);

        return jsonObject;
    }


    @GetMapping("/alldata")
    public JSONObject getAllData() {

        Map<String, Object> data = new HashMap<String, Object>();
        List<Optional<ChatbotConversationRequestDTO>> tmp=new ArrayList<>();
        List<Long> tmpList=chatbotConversationRepository.getAllIds();
        System.out.println("-------------tmpList============="+tmpList);
        for (int i=0;i< tmpList.size();i++){
            Long xLong=new Long(tmpList.get(i));
            System.out.println("========================xLong===================="+xLong);
            System.out.println("====================data info==============="+chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok));
            tmp.add(chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO));

        }
        System.out.println("========================tmp===================="+tmp);
        JSONObject jsonObject=null;
        jsonObject = convertingJsonData(tmp);
        return jsonObject;
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

