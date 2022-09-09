package aehdb.chatbot.model.dto;

import aehdb.chatbot.model.entity.Board2;
import aehdb.chatbot.model.entity.ChatbotConversation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatbotConversationRequestDto {
    private Long id; //아이디
    private String companyid; // 회사아이디
    private String seq; // 대화내용의 순번
    private String text; // 챗봇내용
    private String optionflag; // 옵션 여부

    public ChatbotConversation toEntity() {
        return ChatbotConversation.builder()
                .companyid(companyid)
                .seq(seq)
                .text(text)
                .optionflag(optionflag)
                .build();
    }
}