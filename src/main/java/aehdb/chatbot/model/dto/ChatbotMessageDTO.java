package aehdb.chatbot.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ChatbotMessageDTO {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Item {
		private Long id;
		private String optnNm; //label 로 이름바꿀까?
		private String message;
		private Long parentId;
//		private List<ChildChatbotMessageDTO.Item> childChatBotData; /* ChildChatbotMessageDTO 자식의 자식이 나오는 불필요 연결을 끊는다. */
		private List<ChatbotMessageDTO.Item> childChatBotData; /* ChildChatbotMessageDTO 자식의 자식이 나오는 불필요 연결을 끊는다. */
	}
	
	@Getter
	public static class Request {
		private Long id;
		private String optnNm;
		private String message;
		private Long parentId;
	}

	@Getter
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)	
	public static class Response {
		private Long id;
		private String optnNm;
		private String message;
		private Long parentId;
//		private List<ChildChatbotMessageDTO.Item> childChatBotData;
		private List<ChatbotMessageDTO.Item> childChatBotData;
	}
	

}
