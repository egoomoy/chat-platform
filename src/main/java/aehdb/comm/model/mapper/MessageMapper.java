package aehdb.comm.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aehdb.chat.chat.model.dto.MessageDto;
import aehdb.chat.chat.model.entity.Message;
import aehdb.chat.room.model.dto.RoomDto;
import aehdb.chat.room.model.entity.Room;


@Mapper(componentModel = "spring")
public interface MessageMapper extends GenericMapper<MessageDto, Message> {
	RoomMapper roomMapper = Mappers.getMapper(RoomMapper.class);
	@Override
    public default MessageDto toDto(Message e) {
        if ( e == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setId( e.getId() );
        messageDto.setType( e.getType() );
        messageDto.setSenderId( e.getSenderId() );
        messageDto.setSenderNm( e.getSenderNm() );
        messageDto.setMessage( e.getMessage() );
        
        RoomDto roomDto = roomMapper.toDto(e.getRoom());
        messageDto.setRoomDto(roomDto);
        
        return messageDto;
    }

    @Override
    public default Message toEntity(MessageDto d) {
        if ( d == null ) {
            return null;
        }

        Message message = new Message();

        message.setId( d.getId() );
        message.setType( d.getType() );
        message.setSenderId( d.getSenderId() );
        message.setSenderNm( d.getSenderNm() );
        message.setMessage( d.getMessage() );

        Room room = roomMapper.toEntity(d.getRoomDto());
        message.setRoom(room);
        
        return message;
    }

    @Override
    public default List<MessageDto> toDto(List<Message> e) {
        if ( e == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( e.size() );
        for ( Message message : e ) {
            list.add( toDto( message ) );
        }

        return list;
    }

    @Override
    public default List<Message> toEntity(List<MessageDto> d) {
        if ( d == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( d.size() );
        for ( MessageDto messageDto : d ) {
            list.add( toEntity( messageDto ) );
        }

        return list;
    }

    @Override
    public default void updateFromDto(MessageDto dto, Message entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getType() != null ) {
            entity.setType( dto.getType() );
        }
        if ( dto.getSenderId() != null ) {
            entity.setSenderId( dto.getSenderId() );
        }
        if ( dto.getSenderNm() != null ) {
            entity.setSenderNm( dto.getSenderNm() );
        }
        if ( dto.getMessage() != null ) {
            entity.setMessage( dto.getMessage() );
        }
    }
}
