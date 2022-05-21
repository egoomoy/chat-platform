package aehdb.mng.board.service;

import java.util.List;

import aehdb.mng.board.model.dto.BoardDto;
import aehdb.mng.board.model.dto.BoardSearchDto;

public interface BoardService {
	
	List<BoardDto> selectBoardList(BoardDto boardDto, BoardSearchDto boardSearchDto) throws Exception;
	
	Long insertBoard (BoardDto boardDto) throws Exception;
}
