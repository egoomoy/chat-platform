package aehdb.chatbot.model.repository;

import aehdb.chatbot.model.entity.Board2;
import aehdb.mng.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BRepository extends JpaRepository<Board2, Long> {

}
