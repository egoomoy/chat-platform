package aehdb.chatbot.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import aehdb.comm.model.converter.TFCode;
import aehdb.comm.model.converter.TFCodeConverter;
import aehdb.comm.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicUpdate
public class ChatbotMessage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Convert(converter = TFCodeConverter.class)
//	@Column(columnDefinition = "char")
//	private TFCode hasOptn = TFCode.FALSE;
//	
	@Column(length = 500)
	private String optnNm;
	
	@Column(length = 500)
	private String message;

    @Column(name = "parent_id")
	private Long parentId;

	private Integer seq;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "parentChatBotData")
	private List<ChatbotMessage> childChatBotData =  new ArrayList<ChatbotMessage>();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false)
	private ChatbotMessage parentChatBotData;
	
}