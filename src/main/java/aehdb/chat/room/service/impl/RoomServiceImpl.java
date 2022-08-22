package aehdb.chat.room.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.dto.RoomDto.Item;
import aehdb.chat.room.model.dto.RoomDto.Request;
import aehdb.chat.room.model.entity.Room;
import aehdb.chat.room.model.repository.RoomRepository;
import aehdb.chat.room.service.RoomService;
import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import aehdb.comm.model.mapper.RoomMapperImpl;
import aehdb.mng.legacy.model.dto.LegacyDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomMapperImpl roomMapperImp;
	private final RoomRepository roomRepository;

	public RoomDto.Item findRoomByRoomUuid(UUID uuid) {
		Room room = roomRepository.findRoomByRoomUuid(uuid);
		RoomDto.Item roomDtoItem = roomMapperImp.entitiytoItem(room, new CycleAvoidingMappingContext());
		return roomDtoItem;
	}

	public RoomDto.Item createRoom(RoomDto.Request roomDtoReq) {
		LegacyDto.Item lc = new LegacyDto.Item();
		lc.setId(roomDtoReq.getLegacyId());

		RoomDto.Item roomItem = new RoomDto.Item();
		roomItem.setRoomNm(roomDtoReq.getRoomNm());
		roomItem.setLegacy(lc);
		roomItem.setIsClosed(roomDtoReq.getIsClosed());
		roomItem.setStatus(roomDtoReq.getStatus());

		Room room = roomMapperImp.itemtoEntity(roomItem, new CycleAvoidingMappingContext());
		room = roomRepository.save(room);
		return roomMapperImp.entitiytoItem(room, new CycleAvoidingMappingContext());
	}

	@Override
	public List<RoomDto.Item> selectRoomList() {
		List<Room> roomEntityList = null;
		roomEntityList = roomRepository.findAllByOrderByIdDesc();

		List<RoomDto.Item> tempRoomDtoList = new ArrayList<RoomDto.Item>(roomEntityList.size());
		for (Room item : roomEntityList) {
			tempRoomDtoList.add(roomMapperImp.entitiytoItem(item, new CycleAvoidingMappingContext()));
		}

		return tempRoomDtoList;
	}

	@Override
	public Item updateRoom(Request roomDtoReq) throws Exception {
		Room room = roomRepository.findById(roomDtoReq.getId()).orElse(null);
		room.setStatus(roomDtoReq.getStatus());
		room.setIsClosed(roomDtoReq.getIsClosed());

		room = roomRepository.save(room);
		return roomMapperImp.entitiytoItem(room, new CycleAvoidingMappingContext());
	}

	// 순환 참조 테스트
//	@Override
//	public Room tempfindRoomByRoomUuid(UUID uuid) throws Exception {
//		Room room =roomRepository.findRoomByRoomUuid(uuid);
//		return room;
//
//	}
}
