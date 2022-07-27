package aehdb.chat.message.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import aehdb.chat.room.model.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MessageDto {
	public enum MESSAGETYPE {
		ENTER, MESSAGE, EXIT
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class Item {
		private Long id;
		private UUID roomUuid;
		private MESSAGETYPE type;
		private String senderId;
		private String senderNm;
		private String message;
		private LocalDateTime modified_date;
	}

	@Getter
	@Setter
	public static class Request {
		@NotEmpty(message = "errors.required")
		private UUID roomUuid;
		private MESSAGETYPE type;
		private String senderId;
		private String message;
		private String senderNm;
	}

	@Getter
	@Setter
	@Builder
	public static class Response {
		private Long id;
		private MESSAGETYPE type;
		private UUID roomUuid;
		private String senderId;
		private String senderNm;
		private String message;
	}
}