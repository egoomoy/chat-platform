package aehdb.chat.room.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Convert;

import com.fasterxml.jackson.annotation.JsonInclude;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.comm.model.converter.RoomStatusCode;
import aehdb.comm.model.converter.TFCode;
import aehdb.comm.model.converter.TFCodeConverter;
import aehdb.mng.legacy.model.dto.LegacyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RoomDto {
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Item {
		private Long id;
		private UUID roomUuid;
		private String roomNm;
		private TFCode isClosed = TFCode.FALSE;
		private RoomStatusCode status = RoomStatusCode.OPEN;
		private LegacyDto legacy;
		private LocalDateTime createDate;
		private List<MessageDto.Item> message;
	}

	@Getter
	@Setter
	public static class Request {
		private Long id;
		private String roomNm;
		@Convert(converter = TFCodeConverter.class)
		private TFCode isClosed = TFCode.FALSE;
		private RoomStatusCode status = RoomStatusCode.OPEN;
		private Long legacyId;
	}

	@Getter
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Response {
		private Long id;
		private UUID roomUuid;
		private String roomNm;
		private TFCode isClosed = TFCode.FALSE;
		private RoomStatusCode status = RoomStatusCode.OPEN;
		private LocalDateTime createDate;
		private String lastMessage;
		private LocalDateTime lastMessageDate;

	}

}

// 문제가 뭐냐면 내부 어플리케이션일 때는 화면에서 pk를 암
// 근데 연계 시스템인 경우에는 pk를 알려주거나?
// pk가 아닌 unique key를 알려줘야할 것 같은 기분?
// 근데 또 어차피 내부 어플리케이션에서도 json으로 다 볼 수 있긴한데,, 하씨..
// 빌더로 컨트롤러에서 처리하기로 했는데,맞는 방법인지 모르겠다. 코드 작성에 비효율적인 것 같기도하고..