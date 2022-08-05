package com.community.soccer.repository;

import com.community.soccer.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    //저장 로직
    public void save(Board board) {
        em.persist(board);
    }

    //삭제 로직
    public void remove(Board board) {
        em.remove(board);
    }

    public void update(Board board, String title, String content) {
        board.update(title, content);
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("SELECT b FROM Board AS b", Board.class)
                .getResultList();
    }
}
