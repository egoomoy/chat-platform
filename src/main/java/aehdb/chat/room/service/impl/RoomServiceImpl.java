package aehdb.chat.room.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.entity.Room;
import aehdb.chat.room.model.repository.RoomRepository;
import aehdb.chat.room.service.RoomService;
import aehdb.comm.model.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomMapper roomMapperImp;
	private final RoomRepository roomRepository;

	public RoomDto findRoomByRoomUuid(UUID uuid) {
		final RoomDto roomDto = roomMapperImp.toDto(roomRepository.findRoomByRoomUuid(uuid));
		return roomDto;
	}

	public RoomDto createRoom(RoomDto roomDto) {
		Room room = roomMapperImp.toEntity(roomDto);
		return roomMapperImp.toDto(roomRepository.save(room));
	}
}
