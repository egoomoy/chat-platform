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
public class ChatbotConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; //아이디
    private String companyid; // 회사아이디
    private String seq; // 대화내용의 순번
    private String text; // 챗봇내용
    private String optionflag; // 옵션 여부

    @Builder
    public ChatbotConversation(Long id,String companyid, String seq, String text, String optionflag) {
        this.id=id;
        this.companyid = companyid;
        this.seq = seq;
        this.text = text;
        this.optionflag = optionflag;
    }

}