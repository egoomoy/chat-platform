package aehdb.chatbot.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatbotOptionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이디
    private String companyid; // 회사아이디
    private String seq; // 대화 내용의 순번
    private String text; // 수정일
    private String next; // 다음값


    @Builder
    public ChatbotOptionInfo(Long id,String companyid,String seq, String text, String next) {
        this.id=id;
        this.companyid = companyid;
        this.seq = seq;
        this.text = text;
        this.next = next;
    }
}