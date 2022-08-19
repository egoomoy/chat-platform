package aehdb.chat.message.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;


import aehdb.chat.room.model.dto.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MessageDto {
	public enum MESSAGETYPE {
		ENTER, MESSAGE, EXIT
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public static class Item {
		private Long id;
		private MESSAGETYPE type;
		private String senderId;
		private String senderNm;
		private String message;
		private LocalDateTime modified_date;
		private RoomDto.Item room;
	}

	@Getter
	public static class Request {
		@NotEmpty(message = "errors.required")
		private UUID roomUuid;
		@NotEmpty(message = "errors.required")
		private Long roomId;
		private MESSAGETYPE type;
		private String senderId;
		private String message;
		private String senderNm;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private Long id;
		private Long roomId;
		private MESSAGETYPE type;
		private UUID roomUuid;
		private String senderId;
		private String senderNm;
		private String message;
	}
}