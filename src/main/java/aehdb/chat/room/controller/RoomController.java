package aehdb.chat.room.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.service.RoomService;
import aehdb.mng.legacy.model.dto.LegacyDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // @Controller를 사용할 경우 @Responsebody를 컨트롤러에 명시해주어야 한다.
@RequestMapping("/chat")
public class RoomController {
	private final RoomService roomService;

	@GetMapping("/room/{roomUUID}")
	public ResponseEntity<RoomDto> getRoom(@PathVariable("roomUUID") String id) throws Exception {
		final RoomDto findRoom = roomService.findRoomByRoomUuid(UUID.fromString(id));
		return ResponseEntity.status(HttpStatus.OK).body(findRoom);
	}

	@PostMapping("/room")
	public ResponseEntity<RoomDto> createRoom(@RequestBody @Valid RoomDto roomDto) throws Exception {
		final RoomDto createRoom = roomService.createRoom(roomDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createRoom);
	}
}
