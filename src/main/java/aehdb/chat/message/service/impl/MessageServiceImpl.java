package aehdb.chat.message.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import aehdb.chat.message.model.dto.MessageDto;
import aehdb.chat.message.model.dto.MessageDto.Item;
import aehdb.chat.message.model.entity.Message;
import aehdb.chat.message.model.repository.MessageRepository;
import aehdb.chat.message.service.MessageService;
import aehdb.comm.model.mapper.UpgradeMessageMapperImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;
	private final UpgradeMessageMapperImpl upgradeMessageMapperImpl;

	@Override
	public MessageDto.Item insertMessage(MessageDto.Request req) throws Exception {
		MessageDto.Item messageItem = upgradeMessageMapperImpl.reqToItem(req);
		Message message = upgradeMessageMapperImpl.itemtoEntity(messageItem);
		message = messageRepository.save(message);
		return upgradeMessageMapperImpl.entitiytoItem(message);
	}

	@Override
	public List<Item> selectMessageList(UUID uuid) throws Exception {
		List<Message> messageEntityList = null;
		messageEntityList = messageRepository.findAllByroomUuidOrderByIdAsc(uuid);
		List<MessageDto.Item> MessageDtoList = upgradeMessageMapperImpl.entitiytoItem(messageEntityList);
		return MessageDtoList;
	}

}
