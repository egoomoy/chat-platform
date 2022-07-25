package aehdb.chat.room.service;

import java.util.List;
import java.util.UUID;

import aehdb.chat.room.model.dto.RoomDto;

public interface RoomService {
	public RoomDto.Item findRoomByRoomUuid(UUID uuid) throws Exception;
	
	public RoomDto.Item createRoom(RoomDto.Request roomDtoReq) throws Exception;

	List<RoomDto.Item> selectRoomList() throws Exception;

	// 순환참조 테스트 
//	public Room tempfindRoomByRoomUuid(UUID uuid) throws Exception;
	
	
}
