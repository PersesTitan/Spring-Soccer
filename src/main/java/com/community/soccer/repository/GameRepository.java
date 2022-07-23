package com.community.soccer.repository;

import com.community.soccer.domain.game.Game;
import com.community.soccer.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameRepository {

    private final EntityManager em;

    public void save(Game game) {
        em.persist(game);
    }

    // 삭제
    public void remove(Game game) {
        em.remove(game);
    }

    // 업데이트 로직
    public Game update(Game game, LocalDateTime startDate, LocalDateTime endDate, String region) {
        game.update(startDate, endDate, region);
        return game;
    }

    //게임 조회
    public Game findOne(Long id) {
        return em.find(Game.class, id);
    }

    public List<Game> findAll() {
        return em.createQuery("SELECT G FROM Game AS G", Game.class)
                .getResultList();
    }

    public List<Game> findSearch(LocalDateTime date, String region) {
        return em.createQuery("SELECT G FROM Game G WHERE (:date BETWEEN G.startDate AND G.endDate) AND G.region = :region", Game.class)
                .setParameter("date", date)
                .setParameter("region", region)
                .getResultList();
    }
}
