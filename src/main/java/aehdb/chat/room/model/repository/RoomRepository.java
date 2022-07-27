package aehdb.chat.room.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import aehdb.chat.room.model.entity.Room;

//@Repository

/*
 * https://freedeveloper.tistory.com/131
 * 과거에는 엔티티매니저를 수동으로 생성하여 클래스로 만들었다면,
 * 최근에는 JpaRepository를 이용한다.
 * 복잡한 레포를 짤 때는 엔티티매니저를 사용해야할까?
 */

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findAllByOrderByIdDesc();

	Room findRoomByRoomUuid(UUID uuid);

}
