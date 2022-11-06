package aehdb.chatbot.model.dto;

import java.util.List;

import aehdb.comm.model.converter.TFCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ChatbotMessageDTO {

	@Getter
	@Setter
	@AllArgsConstructor
	public static class Item {
		private Long id;
		private TFCode hasOptn = TFCode.FALSE;
		private String optnNm;
		private String botMessage;
		private Long parentId;
		private Integer seq;
		private List<ChildChatbotMessageDTO.Item> childChatBotData; /* ChildChatbotMessageDTO 자식의 자식이 나오는 불필요 연결을 끊는다. */
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private Long id;
		private TFCode hasOptn = TFCode.FALSE;
		private String optnNm;
		private String botMessage;
		private Long parentId;
		private Integer seq;
		private List<ChildChatbotMessageDTO.Item> childChatBotData;
	}
	
	@Getter
	public static class Request {
		
	}
}
