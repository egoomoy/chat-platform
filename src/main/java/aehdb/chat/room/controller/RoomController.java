package aehdb.chat.room.controller; 

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // @Controller를 사용할 경우 @Responsebody를 컨트롤러에 명시해주어야 한다.
@RequestMapping("/chat")
public class RoomController {
	private final RoomService roomService;

	@GetMapping("/room/{roomUUID}")
	public RoomDto.Response getRoom(@PathVariable("roomUUID") String id) throws Exception {
		RoomDto.Item findRoom = roomService.findRoomByRoomUuid(UUID.fromString(id));

		// 일반적인 경우 서비스에서는 익센션으로 처리되고,
		// 데이터 없음이나 특이점을 보내야하는건가..?
		// 그렇다면, 일반적인 http-status-code는 200으로 고정될 것 같고,
		// 추가로 작성할 메시지
		// 내부 정의되는 코드?
		// 예를 들면 호출된 로우가 없을 경우 데이터 0 이런 것들을 정의해서 사용하는가?
		// 내부 정의 코드는 추가적으로 BaseResponse를 작성하면될 것 같다.

//		RoomDto.Response response =  RoomDto.Response.builder().item(roomDto).build();
//		RoomDto.Response response =  RoomDto.Response.builder().item(roomDto).code(200).build();
		RoomDto.Response response = RoomDto.Response.builder().item(findRoom).code(200).build();
		return response;
	}

	@PostMapping("/room")
	public RoomDto.Response createRoom(@RequestBody @Valid RoomDto.Request roomDtoReq) throws Exception {
		final RoomDto.Item createRoom = roomService.createRoom(roomDtoReq);
		RoomDto.Response response = RoomDto.Response.builder().item(createRoom).code(200).message("").build();
		return response;

	}
	
	
	
	// 순환참조 테스트 
//	@GetMapping("/temproom/{roomUUID}")
//	public Room gettempRoom(@PathVariable("roomUUID") String id) throws Exception {
//		Room findRoom = roomService.tempfindRoomByRoomUuid(UUID.fromString(id));
//		// 일반적인 경우 서비스에서는 익센션으로 처리되고,
//		return findRoom;
//	}
}
