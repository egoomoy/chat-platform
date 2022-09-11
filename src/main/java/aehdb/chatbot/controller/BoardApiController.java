//package aehdb.chatbot.controller;
//
//import aehdb.chatbot.model.dto.BoardRequestDto;
//import aehdb.chatbot.model.dto.BoardResponseDto;
//import aehdb.chatbot.service.BoardService;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor
//public class BoardApiController {
//
//    private final BoardService boardService;
//
//    /**
//     * 게시글 생성
//     */
////    @PostMapping("/chatbot")
////    public Long save(@RequestBody final BoardRequestDto params) {
////        return boardService.save(params);
////    }
//
//    /**
//     * 게시글 리스트 조회
//     */
//    @GetMapping("/chatbot/userss")
//    public List<BoardResponseDto> findAll() {
//        System.out.println("qqqqqq");
//
//        return boardService.findAll();
//    }
//
//
//
//}
