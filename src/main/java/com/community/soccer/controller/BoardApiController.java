package com.community.soccer.controller;

import com.community.soccer.domain.board.Board;
import com.community.soccer.domain.board.dao.BoardCreateDao;
import com.community.soccer.domain.board.dao.BoardEditDao;
import com.community.soccer.domain.board.dao.BoardFindDao;
import com.community.soccer.domain.board.dao.BoardSearchDao;
import com.community.soccer.domain.board.request.BoardCreateRequest;
import com.community.soccer.domain.member.Member;
import com.community.soccer.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardCreateDao saveBoard(@RequestBody @Valid BoardCreateRequest request) {
        String title = request.title();
        String content = request.content();
        Member member = request.member();
        Board board = Board.createBoard(title, content, member);
        return new BoardCreateDao(board.getId());
    }

    @GetMapping("/{id}")
    public BoardFindDao findOne(@PathVariable("id") Long id) {
        Board board = boardService.findOne(id);
        String title = board.getTitle();
        String content = board.getContent();
        Member member = board.getMember();
        LocalDateTime createDate = board.getCreateDate();

        return new BoardFindDao(id, title, content, member, createDate);
    }

    @PatchMapping("/{id}")
    public BoardEditDao boardEdit(@PathVariable("id") Long id) {
        Board board = boardService.findOne(id);
        String title = board.getTitle();
        String content = board.getContent();
        Member member = board.getMember();
        LocalDateTime createDate = board.getCreateDate();
        return new BoardEditDao(id, title, content, member, createDate);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        boardService.remove(id);
    }

    @GetMapping("")
    public BoardSearchDao boardSearchDao(HttpServletRequest request) {
        String title = request.getParameter("title");
        List<Board> collect = new ArrayList<>(boardService.findSearch(title));

        return new BoardSearchDao(collect);
    }
}
