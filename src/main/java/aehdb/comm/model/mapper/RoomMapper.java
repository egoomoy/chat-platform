package aehdb.comm.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.dto.RoomDto.Item;
import aehdb.chat.room.model.dto.RoomDto.Item.ItemBuilder;
import aehdb.chat.room.model.entity.Room;
import aehdb.chat.room.model.entity.Room.RoomBuilder;

@Mapper(componentModel = "spring")
public interface RoomMapper extends GenericMapper<RoomDto.Item, Room> {
	LegacyMapper legacyMapper = Mappers.getMapper(LegacyMapper.class);

	@Override
	public default RoomDto.Item toDto(Room e) {
		if (e == null) {
			return null;
		}

		ItemBuilder item = Item.builder();

		item.id(e.getId());
		item.isClosed(e.getIsClosed());
		item.legacy(legacyMapper.toDto(e.getLegacy()));
		item.roomNm(e.getRoomNm());
		item.roomUuid(e.getRoomUuid());

		return item.build();
	}

	@Override
	public default Room toEntity(RoomDto.Item d) {
		if (d == null) {
			return null;
		}

		RoomBuilder room = Room.builder();

		room.id(d.getId());
		room.isClosed(d.getIsClosed());
		room.legacy(legacyMapper.toEntity(d.getLegacy()));
		room.roomNm(d.getRoomNm());
		room.roomUuid(d.getRoomUuid());

		return room.build();
	}

	@Override
	public default List<RoomDto.Item> toDto(List<Room> e) {
		if (e == null) {
			return null;
		}

		List<RoomDto.Item> list = new ArrayList<RoomDto.Item>(e.size());
		for (Room room : e) {
			list.add(toDto(room));
		}

		return list;
	}

	@Override
	public default List<Room> toEntity(List<RoomDto.Item> d) {
		if (d == null) {
			return null;
		}

		List<Room> list = new ArrayList<Room>(d.size());
		for (RoomDto.Item roomDto : d) {
			list.add(toEntity(roomDto));
		}

		return list;
	}

}
