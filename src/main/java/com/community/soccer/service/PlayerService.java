package com.community.soccer.service;

import com.community.soccer.domain.game.Game;
import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.item.PositionDto;
import com.community.soccer.domain.player.Player;
import com.community.soccer.exception.PageRequestException;
import com.community.soccer.repository.GameRepository;
import com.community.soccer.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    public Player save(Long gameId, Position position, String phone) {
        Player player = Player.createPlayer(position, phone);
        Game game = gameRepository.findOne(gameId);
        player.setGame(game);
        playerRepository.save(player);
        return player;
    }

    public List<Player> findAll(Long gameId) {
        return gameRepository.findOne(gameId).getPlayers();
    }

    @Transactional
    public void remove(Long gameId, Long playerId) {
        List<Player> players = gameRepository
                .findOne(gameId)
                .getPlayers()
                .stream()
                .filter(Objects::nonNull)
                .filter(v -> Objects.equals(v.getId(), playerId))
                .findFirst()
                .stream()
                .toList();
        if (players.isEmpty()) throw new PageRequestException("페이지 요청 요류");
        playerRepository.remove(players.get(0));
    }

}
