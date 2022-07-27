package aehdb.chat.room.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.dto.RoomDto.Item;
import aehdb.chat.room.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // @Controller를 사용할 경우 @Responsebody를 컨트롤러에 명시해주어야 한다.
@RequestMapping("/chat")
public class RoomController {
	private final RoomService roomService;

	@GetMapping("/rooms")
	public List<RoomDto.Response> getRooms() throws Exception {
		List<RoomDto.Item> roomList = roomService.selectRoomList();
		List<RoomDto.Response> responseRoomList = new ArrayList<RoomDto.Response>();

//		roomList.sort(new Comparator<RoomDto.Item>() {
//			@Override
//			public int compare(RoomDto.Item first, RoomDto.Item second) {
//				return second.getLastMessage().getId().intValue() - first.getLastMessage().getId().intValue();
//			}
//		}); 
		for (Item rtnRoom : roomList) {
			if (rtnRoom.getLastMessage() != null) {
				RoomDto.Response response = RoomDto.Response.builder()
						.id(rtnRoom.getId())
						.roomUuid(rtnRoom.getRoomUuid())
						.roomNm(rtnRoom.getRoomNm())
						.isClosed(rtnRoom.getIsClosed())
						.createDate(rtnRoom.getCreateDate())
						.lastMessage(rtnRoom.getLastMessage().getMessage())
						.lastMessageDate(rtnRoom.getLastMessage().getModified_date())
						.build();
				responseRoomList.add(response);
			}
		}
		return responseRoomList;
	}

	@GetMapping("/room/{roomUUID}")
	public RoomDto.Response getRoom(@PathVariable("roomUUID") String id) throws Exception {
		RoomDto.Item rtnRoom = roomService.findRoomByRoomUuid(UUID.fromString(id));
		RoomDto.Response response = RoomDto.Response.builder()
				.id(rtnRoom.getId())
				.roomUuid(rtnRoom.getRoomUuid())
				.roomNm(rtnRoom.getRoomNm())
				.isClosed(rtnRoom.getIsClosed())
				.createDate(rtnRoom.getCreateDate())
				.build();
		return response;
	}

	@PostMapping("/room")
	public RoomDto.Response createRoom(@RequestBody @Valid RoomDto.Request roomDtoReq) throws Exception {
		final RoomDto.Item rtnRoom = roomService.createRoom(roomDtoReq);
		RoomDto.Response response = RoomDto.Response.builder()
				.id(rtnRoom.getId())
				.roomUuid(rtnRoom.getRoomUuid())
				.roomNm(rtnRoom.getRoomNm())
				.isClosed(rtnRoom.getIsClosed())
				.createDate(rtnRoom.getCreateDate())
				.build();
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
