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


    public void convertingJsonData(List<Optional<ChatbotConversationRequestDTO>> tmp) {
        Map<String,Object> jsonConvert=new HashMap<>();
        Map<String,Object> jsonConvertResult=new HashMap<>();
        List res=new ArrayList<Map<String,Object>>();


        List<Map> jsonResult=new ArrayList<>();
        List<Map> optionsList=new ArrayList<>();

        for (int i=0;i<tmp.size();i++){
            Optional<ChatbotConversationRequestDTO> oDto=tmp.get(i);
            jsonConvertResult.clear();
            jsonConvert.clear();
            optionsList.clear();
            for (ChatbotConversation chatbotConversation : oDto.get().getChildChatbotConversation()) {
                Map<String,Object> options=new HashMap<>();
                options.put("text",chatbotConversation.getText());
                options.put("next",chatbotConversation.getNext());
                optionsList.add(options);
            }

            jsonConvert.put("text",oDto.get().getText());
            jsonConvert.put("options",optionsList);
            String s=String.valueOf(i+1);

//            res.add(jsonConvert);
            jsonConvertResult.put(s,jsonConvert);

            res.add(jsonConvertResult);
            JSONObject jsonObject = new JSONObject(jsonConvertResult);
            System.out.println("=========jsonObject==========="+res);

//            JSONObject jsonObject2 = new JSONObject(jsonConvertResult);
//            System.out.println("============final============"+jsonObject2);
//            jsonConvert.put(s,jsonConvert);
//            JSONObject jsonObject = new JSONObject(jsonConvert);
//            jsonResult.add(jsonObject);
//            System.out.println("===========================json======================"+jsonResult);


        }




    }
//    public List<Optional<ChatbotConversationRequestDTO>> convertingJsonData(List<Optional<ChatbotConversationRequestDTO>> tmp) {
//        System.out.println("==========passing data=========="+tmp);
//    }

    @GetMapping("/alldata")
    public List<Optional<ChatbotConversationRequestDTO>> getAllData() {

        Map<String, Object> data = new HashMap<String, Object>();
        List<Optional<ChatbotConversationRequestDTO>> tmp=new ArrayList<>();
        List<Long> tmpList=chatbotConversationRepository.getAllseqs();
        System.out.println("----------------------"+chatbotConversationRepository.findById(new Long(1)).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok));
        for (int i=1;i< tmpList.size()+1;i++){
            Long xLong=new Long(i);
//            System.out.println("====================data info==============="+chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO).map(ResponseEntity::ok));
            tmp.add(chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO));

        }
        convertingJsonData(tmp);
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

