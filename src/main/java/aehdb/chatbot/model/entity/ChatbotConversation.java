package aehdb.chatbot.model.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String next; // 챗봇내용
    private String optionflag; // 옵션 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private ChatbotConversation parentChatbotConversation;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parentChatbotConversation")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<ChatbotConversation> childChatbotConversation;


    @JsonIgnore
    public Set<ChatbotConversation> getchildChatbotConversation() {
        return childChatbotConversation;
    }

//    @Builder
//    public ChatbotConversation(Long id,String companyid, String seq, String text, String optionflag) {
//        this.id=id;
//        this.companyid = companyid;
//        this.seq = seq;
//        this.text = text;
//        this.optionflag = optionflag;
//    }

}