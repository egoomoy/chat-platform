package aehdb.mng.board.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aehdb.mng.board.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findAllByOrderByIdDesc();

	List<Board> findByTitleContainsOrderByIdDesc(String title);

}
