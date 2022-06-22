package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.chat.message.model.entity.Message;


@Mapper(componentModel = "spring")
public interface MessageMapperAuto extends GenericMapper<MessageDto, Message> {

}
