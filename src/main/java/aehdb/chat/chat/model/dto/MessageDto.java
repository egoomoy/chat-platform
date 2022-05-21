package aehdb.chat.chat.model.dto;

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
	private Long roomId;
	private String senderId;
	private String senderNm;
	private String message;
}
