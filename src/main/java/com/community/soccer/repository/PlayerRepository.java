package com.community.soccer.repository;

import com.community.soccer.domain.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayerRepository {

    private final EntityManager em;

    public void save(Player player) {
        em.persist(player);
    }

    public void remove(Player player) {
        em.remove(player);
    }

    //Player 조회하는 로직
    public Player findOne(Long id) {
        return em.find(Player.class, id);
    }

    //모든 Player 조회하는 로직
    public List<Player> findAll() {
        return em.createQuery("SELECT P FROM Player AS P", Player.class)
                .getResultList();
    }
}
