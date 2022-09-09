package aehdb.chatbot.model.dto;

import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.chatbot.model.entity.ChatbotOptionInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatbotOptionRequestDto {
    private Long id; // 아이디
    private String companyid; // 회사아이디
    private String seq; // 대화 내용의 순번
    private String text; // 수정일
    private String next; // 다음값

    public ChatbotOptionInfo toEntity() {
        return ChatbotOptionInfo.builder()
                .companyid(companyid)
                .seq(seq)
                .text(text)
                .next(next)
                .build();
    }
}