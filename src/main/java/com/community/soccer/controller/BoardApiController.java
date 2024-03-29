package com.community.soccer.controller;

import com.community.soccer.domain.board.Board;
import com.community.soccer.domain.board.dao.BoardCreateDao;
import com.community.soccer.domain.board.dao.BoardEditDao;
import com.community.soccer.domain.board.dao.BoardFindDao;
import com.community.soccer.domain.board.dao.BoardSearchDao;
import com.community.soccer.domain.board.request.BoardCreateRequest;
import com.community.soccer.domain.member.Member;
import com.community.soccer.error.ErrorCreate;
import com.community.soccer.service.BoardService;
import com.community.soccer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final MemberService memberService;
    private final BoardService boardService;

    @PostMapping("")
    public BoardCreateDao saveBoard(
            @RequestBody @Valid BoardCreateRequest request,
            @CookieValue(name = "memberId", required = false) Long memberId) {
        // 로그인이 되어있지 않으면 에러 발생
        if (memberId == null) throw new ErrorCreate("로그인이 필요합니다.");
        String title = request.title();
        String content = request.content();
        Member member = memberService.findOne(memberId);
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
    public List<BoardSearchDao> boardSearchDao(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        return boardService.findSearch(title, page)
                .stream()
                .map(value -> new BoardSearchDao(value))
                .collect(Collectors.toList());
    }
}
