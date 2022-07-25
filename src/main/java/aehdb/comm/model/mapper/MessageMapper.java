package aehdb.comm.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.chat.message.model.dto.MessageDto.Item;
import aehdb.chat.message.model.dto.MessageDto.Item.ItemBuilder;
import aehdb.chat.message.model.entity.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper extends GenericMapper<MessageDto.Item, Message> {
	@Override
	public default Item toDto(Message e) {
		if (e == null) {
			return null;
		}

		ItemBuilder item = Item.builder();

		item.roomUuid(e.getRoomUuid());
		item.id(e.getId());
		item.type(e.getType());
		item.senderId(e.getSenderId());
		item.senderNm(e.getSenderNm());
		item.message(e.getMessage());

		return item.build();
	}

	@Override
	public default Message toEntity(Item d) {
		if (d == null) {
			return null;
		}

		Message message = new Message();

		message.setRoomUuid(d.getRoomUuid());
		message.setId(d.getId());
		message.setType(d.getType());
		message.setSenderId(d.getSenderId());
		message.setSenderNm(d.getSenderNm());
		message.setMessage(d.getMessage());

		return message;
	}

	@Override
	public default List<Item> toDto(List<Message> e) {
		if (e == null) {
			return null;
		}

		List<Item> list = new ArrayList<Item>(e.size());
		for (Message message : e) {
			list.add(toDto(message));
		}

		return list;
	}

	@Override
	public default List<Message> toEntity(List<Item> d) {
		if (d == null) {
			return null;
		}

		List<Message> list = new ArrayList<Message>(d.size());
		for (Item item : d) {
			list.add(toEntity(item));
		}

		return list;
	}

	@Override
	public default void updateFromDto(Item dto, Message entity) {
		if (dto == null) {
			return;
		}

		if(dto.getRoomUuid() != null) {
			entity.setRoomUuid(dto.getRoomUuid());
		}
		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
		if (dto.getType() != null) {
			entity.setType(dto.getType());
		}
		if (dto.getSenderId() != null) {
			entity.setSenderId(dto.getSenderId());
		}
		if (dto.getSenderNm() != null) {
			entity.setSenderNm(dto.getSenderNm());
		}
		if (dto.getMessage() != null) {
			entity.setMessage(dto.getMessage());
		}
	}
}
