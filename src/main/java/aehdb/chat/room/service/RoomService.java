package aehdb.chat.room.service;

import java.util.UUID;

import aehdb.chat.room.model.dto.RoomDto;

public interface RoomService {
	public RoomDto findRoomByRoomUuid(UUID uuid) throws Exception;
	
	public RoomDto createRoom(RoomDto roomDto) throws Exception;
}
