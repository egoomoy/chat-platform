package aehdb.chatbot.service;

import java.util.List;
import java.util.stream.Collectors;


import aehdb.chatbot.model.dto.BoardRequestDto;
import aehdb.chatbot.model.dto.BoardResponseDto;
import aehdb.chatbot.model.entity.Board2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BRepository boardRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final BoardRequestDto params) {
        Board2 entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardResponseDto> findAll() {

        Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
        List<Board2> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
}

