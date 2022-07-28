package aehdb.chat.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.chat.message.model.dto.MessageDto.Item;
//import aehdb.chat.message.model.dto.MessageDto.Item.ItemBuilder;
import aehdb.chat.message.model.entity.Message;
import aehdb.chat.message.model.repository.MessageRepository;
import aehdb.chat.message.service.MessageService;
import aehdb.chat.room.model.dto.RoomDto;
import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import aehdb.comm.model.mapper.UpgradeMessageMapperImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;
	private final UpgradeMessageMapperImpl upgradeMessageMapperImpl;

	@Override
	public MessageDto.Item insertMessage(MessageDto.Request rq) throws Exception {
		MessageDto.Item messageItem = upgradeMessageMapperImpl.reqToItem(rq);

		RoomDto.Item room = new RoomDto.Item();
		room.setId(rq.getRoomId());
		room.setRoomUuid(rq.getRoomUuid());
		
		messageItem.setRoom(room);
		Message message = upgradeMessageMapperImpl.itemtoEntity(messageItem, new CycleAvoidingMappingContext());
		message = messageRepository.save(message);
		return upgradeMessageMapperImpl.entitiytoItem(message, new CycleAvoidingMappingContext());
	}

	@Override
	public List<Item> selectMessageList(Long id) throws Exception {
		List<Message> messageEntityList = null;
		messageEntityList = messageRepository.findAllByroomIdOrderByIdAsc(id);

		List<MessageDto.Item> MessageDtoList = new ArrayList<MessageDto.Item>(messageEntityList.size());
		for (Message item : messageEntityList) {
			MessageDtoList.add(upgradeMessageMapperImpl.entitiytoItem(item, new CycleAvoidingMappingContext()));
		}

		return MessageDtoList;
	}

}
