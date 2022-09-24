//package aehdb.chatbot.service;
//
//import aehdb.chatbot.model.dto.BoardRequestDto;
//import aehdb.chatbot.model.dto.BoardResponseDto;
//import aehdb.chatbot.model.dto.ChatbotConversationRequestDto;
//import aehdb.chatbot.model.dto.ChatbotOptionRequestDto;
//import aehdb.chatbot.model.entity.Board2;
//import aehdb.chatbot.model.entity.ChatbotConversation;
//import aehdb.chatbot.model.entity.ChatbotOptionInfo;
//import aehdb.chatbot.model.repository.BRepository;
//import aehdb.chatbot.model.repository.ChatbotConversationRepository;
//import aehdb.chatbot.model.repository.ChatbotOptionRepository;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.builder.ToStringExclude;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ChatbotService {
//
//    private final ChatbotConversationRepository chatbotconvRepository;
//    private final ChatbotOptionRepository chatbotOptionRepository;
//    /**
//     * 봇 대화 저장
//     */
//    @Transactional
//    public ChatbotConversation save(final ChatbotConversationRequestDto params) {
//        ChatbotConversation entity = chatbotconvRepository.save(params.toEntity());
//  //      test();
//        return entity;
//    }
//
//    @Transactional
//    public ChatbotOptionInfo save(final ChatbotOptionRequestDto params) {
//        ChatbotOptionInfo entity = chatbotOptionRepository.save(params.toEntity());
// //       test();
//        return entity;
//    }
//
//    public void test() {
//        ChatbotOptionInfo oi=ChatbotOptionInfo.builder()
//                .companyid("companyid")
//                .seq("seq")
//                .text("TEST")
//                .next("NEXT")
//                .build();
//        ChatbotConversation ci=ChatbotConversation.builder()
//                .companyid("companyid")
//                .seq("seq")
//                .text("TEST")
//                .optionflag("Y")
//                .build();
//        ci.setChatbotOptionInfo(oi);
//        chatbotconvRepository.save(ci);
//
//    }
//
//
//
//
//
//
//
//
//}
