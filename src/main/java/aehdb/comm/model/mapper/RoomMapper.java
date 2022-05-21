package aehdb.comm.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.entity.Room;
import aehdb.mng.legacy.model.dto.LegacyDto;
import aehdb.mng.legacy.model.entity.Legacy;

@Mapper(componentModel = "spring")
public interface RoomMapper extends GenericMapper<RoomDto, Room> {
		LegacyMapper legacyMapper = Mappers.getMapper(LegacyMapper.class);
		@Override
	    public default RoomDto toDto(Room e) {
	        if ( e == null ) {
	            return null;
	        }

	        RoomDto roomDto = new RoomDto();

	        roomDto.setId( e.getId() );
	        roomDto.setRoomUuid( e.getRoomUuid() );
	        roomDto.setRoomNm( e.getRoomNm() );
	        roomDto.setIsClosed( e.getIsClosed() );

	        LegacyDto legacyDto = legacyMapper.toDto(e.getLegacy());
	        roomDto.setLegacyDto(legacyDto);
	        
	        return roomDto;
	    }

	    @Override
	    public default Room toEntity(RoomDto d) {
	        if ( d == null ) {
	            return null;
	        }

	        Room room = new Room();

	        room.setId( d.getId() );
	        room.setRoomUuid( d.getRoomUuid() );
	        room.setIsClosed( d.getIsClosed() );
	        room.setRoomNm( d.getRoomNm() );
	        
	        Legacy legacy = legacyMapper.toEntity(d.getLegacyDto());
	        room.setLegacy(legacy);

	        return room;
	    }

	    @Override
	    public default List<RoomDto> toDto(List<Room> e) {
	        if ( e == null ) {
	            return null;
	        }

	        List<RoomDto> list = new ArrayList<RoomDto>( e.size() );
	        for ( Room room : e ) {
	            list.add( toDto( room ) );
	        }

	        return list;
	    }

	    @Override
	    public default List<Room> toEntity(List<RoomDto> d) {
	        if ( d == null ) {
	            return null;
	        }

	        List<Room> list = new ArrayList<Room>( d.size() );
	        for ( RoomDto roomDto : d ) {
	            list.add( toEntity( roomDto ) );
	        }

	        return list;
	    }

	    @Override
	    public default void updateFromDto(RoomDto dto, Room entity) {
	        if ( dto == null ) {
	            return;
	        }

	        if ( dto.getId() != null ) {
	            entity.setId( dto.getId() );
	        }
	        if ( dto.getRoomUuid() != null ) {
	            entity.setRoomUuid( dto.getRoomUuid() );
	        }
	        if ( dto.getIsClosed() != null ) {
	            entity.setIsClosed( dto.getIsClosed() );
	        }
	        if ( dto.getRoomNm() != null ) {
	            entity.setRoomNm( dto.getRoomNm() );
	        }
	    }
}
