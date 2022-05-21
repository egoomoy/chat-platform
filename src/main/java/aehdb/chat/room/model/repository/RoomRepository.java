package aehdb.chat.room.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aehdb.chat.room.model.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	List<Room> findAll();

	Room findRoomByRoomUuid(UUID uuid);
}
