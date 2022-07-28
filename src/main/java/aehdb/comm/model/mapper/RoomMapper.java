package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.entity.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper extends UpgradeGenericMapper<RoomDto.Item, Room, RoomDto.Request, RoomDto.Response> {

}
