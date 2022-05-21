package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.mng.board.model.dto.BoardDto;
import aehdb.mng.board.model.entity.Board;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapper<BoardDto, Board> {

}
