package aehdb.chat.message.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import aehdb.chat.message.model.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findAll();

	List<Message> findAllByroomUuid(UUID roomUuid);
	List<Message> findAllByroomUuidOrderByIdAsc(UUID roomUuid);

	Optional<Message> findTop1ByroomUuid(UUID roomUuid);

}
