package aehdb.chat.chat.model.dto;

import aehdb.chat.room.model.dto.RoomDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
	public enum MESSAGETYPE {
		ENTER, MESSAGE, EXIT
	}

	private Long id;
	private MESSAGETYPE type;
	private RoomDto roomDto;
	private String senderId;
	private String senderNm;
	private String message;
}
