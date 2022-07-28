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
		// 만약 여기서 req 객체를 받았다고 가정해보자
		// 난 그럼 req 객체를 토대로 Item을 세팅해주는건가..? AllArgsConstructor 든 Builder 든으로 생성을 한다?
		// item을 빌더로 만들 경우에는 확실히 객체의 불변성을 보장해준다?
		// item을 entity화 시켜주고 로직 처리를 한다.
		// entity를 response화 하면된다?
		// 실제 데이터는 req에서 조작되어야하고,
		// Res와 item은 불변성 유지해야한다.
		Room room = roomRepository.findRoomByRoomUuid(uuid);
		RoomDto.Item roomDtoItem = roomMapperImp.entitiytoItem(room, new CycleAvoidingMappingContext());
		return roomDtoItem;
	}

	public RoomDto.Item createRoom(RoomDto.Request roomDtoReq) {
		LegacyDto lc = new LegacyDto();
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
