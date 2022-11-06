package aehdb.chatbot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ChildChatbotMessageDTO {

	@Getter
	@Setter
	@AllArgsConstructor
	public static class Item {
		private Long id;
		private String optnNm;
		private String message;
		private Long parentId;
//		private Integer seq;

	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private Long id;
		private String optnNm;
		private String message;
		private Long parentId;
//		private Integer seq;
	}

	@Getter
	public static class Request {

	}
}
