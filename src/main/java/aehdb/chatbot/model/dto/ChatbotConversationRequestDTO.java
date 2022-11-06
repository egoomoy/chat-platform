package aehdb.chatbot.model.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import aehdb.chatbot.model.entity.ChatbotConversation;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class ChatbotConversationRequestDTO {

    private Long id;
    private String companyid;
    private String seq;
    private String text;
    //private String text;
    private ChatbotConversation parentChatbotConversation;
    private Set<ChatbotConversation> childChatbotConversation;
}
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class ChatbotConversationRequestDto {
//    private Long id; //아이디
//    private String companyid; // 회사아이디
//    private String seq; // 대화내용의 순번
//    private String text; // 챗봇내용
//    private String optionflag; // 옵션 여부
//
//    public ChatbotConversation toEntity() {
//        return ChatbotConversation.builder()
//                .companyid(companyid)
//                .seq(seq)
//                .text(text)
//                .optionflag(optionflag)
//                .build();
//    }
//}