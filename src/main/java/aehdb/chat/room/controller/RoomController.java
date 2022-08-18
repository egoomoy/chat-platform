package aehdb.chat.room.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.dto.RoomDto.Item;
import aehdb.chat.room.model.dto.RoomDto.Response.ResponseBuilder;
import aehdb.chat.room.service.RoomService;
import aehdb.comm.model.dto.ResponseMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // @Controller를 사용할 경우 @Responsebody를 컨트롤러에 명시해주어야 한다.
//@RequestMapping("/chat")
public class RoomController {
	private final RoomService roomService;

	@PostMapping("/chat/room")
	public ResponseMap createRoom(@RequestBody @Valid RoomDto.Request roomDtoReq) throws Exception {
		final RoomDto.Item rtnRoom = roomService.createRoom(roomDtoReq);
		RoomDto.Response response = RoomDto.Response.builder()
				.id(rtnRoom.getId())
				.roomUuid(rtnRoom.getRoomUuid())
				.roomNm(rtnRoom.getRoomNm())
				.isClosed(rtnRoom.getIsClosed())
				.createDate(rtnRoom.getCreateDate())
				.status(rtnRoom.getStatus())
				.build();
		return new ResponseMap(200, "", response);
	}

	@GetMapping("/mng/rooms")
	public ResponseMap getRooms() throws Exception {
		List<RoomDto.Item> roomList = roomService.selectRoomList();
		List<RoomDto.Response> responseRoomList = new ArrayList<RoomDto.Response>();

		for (Item rtnRoom : roomList) {
			ResponseBuilder rb = RoomDto.Response.builder();
			rb.id(rtnRoom.getId());
			rb.roomUuid(rtnRoom.getRoomUuid());
			rb.roomNm(rtnRoom.getRoomNm());
			rb.isClosed(rtnRoom.getIsClosed());
			rb.createDate(rtnRoom.getCreateDate());
			rb.status(rtnRoom.getStatus());
			if (rtnRoom.getMessage().size() != 0) {
				rb.lastMessage(rtnRoom.getMessage().get(0).getMessage());
				rb.lastMessageDate(rtnRoom.getMessage().get(0).getModified_date());
			}
			responseRoomList.add(rb.build());
//			}
		}
		return new ResponseMap(200, "", responseRoomList);
	}

	@GetMapping("/mng/room/{roomUUID}")
	public ResponseMap getRoom(@PathVariable("roomUUID") String id) throws Exception {
		RoomDto.Item rtnRoom = roomService.findRoomByRoomUuid(UUID.fromString(id));
		RoomDto.Response response = RoomDto.Response.builder()
				.id(rtnRoom.getId())
				.roomUuid(rtnRoom.getRoomUuid())
				.roomNm(rtnRoom.getRoomNm())
				.isClosed(rtnRoom.getIsClosed())
				.createDate(rtnRoom.getCreateDate())
				.status(rtnRoom.getStatus())
				.build();
		return new ResponseMap(200, "", response);
	}

	@PatchMapping("/mng/room")
	public ResponseMap updateRoom(@RequestBody @Valid RoomDto.Request roomDtoReq) throws Exception {
		final RoomDto.Item rtnRoom = roomService.updateRoom(roomDtoReq);

		ResponseBuilder rb = RoomDto.Response.builder();
		rb.id(rtnRoom.getId());
		rb.roomUuid(rtnRoom.getRoomUuid());
		rb.roomNm(rtnRoom.getRoomNm());
		rb.isClosed(rtnRoom.getIsClosed());
		rb.createDate(rtnRoom.getCreateDate());
		rb.status(rtnRoom.getStatus());
		if (rtnRoom.getMessage().size() != 0) {
			rb.lastMessage(rtnRoom.getMessage().get(0).getMessage());
			rb.lastMessageDate(rtnRoom.getMessage().get(0).getModified_date());
		}
		RoomDto.Response response = rb.build();
		return new ResponseMap(200, "", response);
	}

	// 순환참조 테스트
//	@GetMapping("/temproom/{roomUUID}")
//	public Room gettempRoom(@PathVariable("roomUUID") String id) throws Exception {
//		Room findRoom = roomService.tempfindRoomByRoomUuid(UUID.fromString(id));
//		// 일반적인 경우 서비스에서는 익센션으로 처리되고,
//		return findRoom;
//	}
}
