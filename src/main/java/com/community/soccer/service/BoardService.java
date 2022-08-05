package com.community.soccer.service;

import com.community.soccer.domain.board.Board;
import com.community.soccer.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void update(Long id, String title, String content) {
        Board board = boardRepository.findOne(id);
        boardRepository.update(board, title, content);
    }

    @Transactional
    public void remove(Long id) {
        Board board = boardRepository.findOne(id);
        boardRepository.remove(board);
    }

    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
