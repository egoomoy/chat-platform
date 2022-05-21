package aehdb.mng.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aehdb.comm.model.mapper.BoardMapper;
import aehdb.mng.board.model.dto.BoardDto;
import aehdb.mng.board.model.dto.BoardSearchDto;
import aehdb.mng.board.model.entity.Board;
import aehdb.mng.board.model.repository.BoardRepository;
import aehdb.mng.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final BoardMapper boardMapperImpl;

	@Override
	@Transactional(readOnly = true)
	public List<BoardDto> selectBoardList(BoardDto boardDto, BoardSearchDto boardSearchDto) throws Exception {
		// TODO Auto-generated method stub
		List<Board> boardEntityList = null;

		String searchTitle = boardSearchDto.getSearchTitle();
		if ("".equals(searchTitle)) {
			boardEntityList = boardRepository.findAllByOrderByIdDesc();
		} else {
			boardEntityList = boardRepository.findByTitleContainsOrderByIdDesc(searchTitle);
		}

		List<BoardDto> boardDtoList = boardMapperImpl.toDto(boardEntityList);
		
		return boardDtoList;
	}

	@Override
	@Transactional
	public Long insertBoard(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
		Board board = boardMapperImpl.toEntity(boardDto);
		boardDto = boardMapperImpl.toDto(boardRepository.save(board));

		return boardDto.getId();
	}
}
