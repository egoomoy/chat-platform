package aehdb.chat.room.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.entity.Room;
import aehdb.chat.room.model.repository.RoomRepository;
import aehdb.chat.room.service.RoomService;
import aehdb.comm.model.mapper.RoomMapper;
import aehdb.mng.legacy.model.dto.LegacyDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomMapper roomMapperImp;
	private final RoomRepository roomRepository;

	public RoomDto.Item findRoomByRoomUuid(UUID uuid) {
		// 만약 여기서 req 객체를 받았다고 가정해보자
		// 난 그럼 req 객체를 토대로 Item을 세팅해주는건가..? AllArgsConstructor 든 Builder 든으로 생성을 한다?
		// item을 빌더로 만들 경우에는 확실히 객체의 불변성을 보장해준다?
		// item을 entity화 시켜주고 로직 처리를 한다.
		// entity를 response화 하면된다?
		// 실제 데이터는 req에서 조작되어야하고,
		// Res와 item은 불변성 유지해야한다.
		Room room =roomRepository.findRoomByRoomUuid(uuid);
		
		RoomDto.Item roomDtoItem = roomMapperImp.toDto(room);

		return roomDtoItem;
	}

	public RoomDto.Item createRoom(RoomDto.Request roomDtoReq) {
		LegacyDto lc = new LegacyDto();
		lc.setId(roomDtoReq.getLegacyId());
		
		RoomDto.Item roomItem = RoomDto.Item.builder()
				.roomNm(roomDtoReq.getRoomNm())
				.isClosed(roomDtoReq.getIsClosed())
				.legacy(lc)
				.build();

		Room room = roomMapperImp.toEntity(roomItem);
		room = roomRepository.save(room);
		
		return roomMapperImp.toDto(room);
	}

	
	// 순환 참조 테스트
//	@Override
//	public Room tempfindRoomByRoomUuid(UUID uuid) throws Exception {
//		// 만약 여기서 req 객체를 받았다고 가정해보자
//		// 난 그럼 req 객체를 토대로 Item을 세팅해주는건가..? AllArgsConstructor 든 Builder 든으로 생성을 한다?
//		// item을 빌더로 만들 경우에는 확실히 객체의 불변성을 보장해준다?
//		// item을 entity화 시켜주고 로직 처리를 한다.
//		// entity를 response화 하면된다?
//		// 실제 데이터는 req에서 조작되어야하고,
//		// Res와 item은 불변성 유지해야한다.
//		Room room =roomRepository.findRoomByRoomUuid(uuid);
//		return room;
//
//	}
}
