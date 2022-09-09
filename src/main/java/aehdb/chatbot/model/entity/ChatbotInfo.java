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
public class ChatbotInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이디
    private String companyid; // 회사아이디
    private String chatbotid; // 챗봇아이디

    private LocalDateTime create_date; // 생성일
    private LocalDateTime modified_date; // 수정일
    private String creator; // 생성자

}