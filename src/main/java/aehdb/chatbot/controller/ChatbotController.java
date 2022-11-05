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
        System.out.println("============================alltmp==================="+tmp);

        Map<String,Object> jsonConvertResult=new HashMap<>();
        //질문 대 카테고리(부모)
        for (int i=0;i<tmp.size();i++){
            Map<String,Object> jsonConvert=new LinkedHashMap<>();
            Optional<ChatbotConversationRequestDTO> oDto=tmp.get(i);
            List<Map> optionsList=new ArrayList<>();
            jsonConvert.put("text",oDto.get().getText());
            if  (oDto.get().getChildChatbotConversation().size()!=0) {
                Set<ChatbotConversation> childSet= oDto.get().getChildChatbotConversation();
                Map<Integer, String> testMap = new HashMap<Integer, String>();
                // 질문소 카테고리(자식)
                // Set방식은 순번을 지켜서 받아오지 않음
                for (ChatbotConversation child : childSet) {
                    testMap.put(Integer.parseInt(child.getNext()),child.getText());
                }
                //object를통한 next키값을 정렬
                Object[] mapkey = testMap.keySet().toArray();
                Arrays.sort(mapkey);
                //정렬후 순차적으로 리턴
                for (Integer nKey : testMap.keySet())
                {

                    Map<String, Object> options = new LinkedHashMap<>();
                    options.put("text", testMap.get(nKey));
                    options.put("next", nKey.toString());
                    optionsList.add(options);

                }



                jsonConvert.put("options",List.copyOf(optionsList));
                optionsList.clear();
            }

            String s=oDto.get().getSeq().toString();
            jsonConvertResult.put(s,jsonConvert);


        }
        System.out.println("===================test123=========="+jsonConvertResult);
        JSONObject jsonObject = new JSONObject(jsonConvertResult);


        return jsonObject;
    }


    @GetMapping("/alldata")
    public JSONObject getAllData() {

        Map<String, Object> data = new HashMap<String, Object>();
        List<Optional<ChatbotConversationRequestDTO>> tmp=new ArrayList<>();
        List<Long> tmpList=chatbotConversationRepository.getAllIds();
        Collections.sort(tmpList);
        System.out.println("-------------tmpList============="+tmpList);
        for (int i=0;i< tmpList.size();i++){
            Long xLong=new Long(tmpList.get(i));
            tmp.add(chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO));
            System.out.println("============================test==================="+chatbotConversationRepository.findById(xLong).map(mapToChatbotConversationRequestDTO));

        }

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
