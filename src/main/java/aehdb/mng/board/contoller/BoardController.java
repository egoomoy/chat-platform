package aehdb.mng.board.contoller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aehdb.comm.message.MessageUtil;
import aehdb.mng.board.model.dto.BoardDto;
import aehdb.mng.board.model.dto.BoardSearchDto;
import aehdb.mng.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@GetMapping(value = "/mng/boards")
	public List<BoardDto> boardList(@RequestBody @Valid BoardDto boardDto, BoardSearchDto boardSearchDto)
			throws Exception {
		return boardService.selectBoardList(boardDto, boardSearchDto);
	}

	@PostMapping(value = "/mng/board")
	public HashMap<String, String> insertBoard(@RequestBody @Valid BoardDto boardDto) throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		boardService.insertBoard(boardDto);
		resultMap.put("message", MessageUtil.getMessage("info.success.insert"));

		return resultMap;
	}

}
