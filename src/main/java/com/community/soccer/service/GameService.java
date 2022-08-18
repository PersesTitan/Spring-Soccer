package com.community.soccer.service;

import com.community.soccer.domain.game.Game;
import com.community.soccer.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public Long save(Game game) {
        gameRepository.save(game);
        return game.getId();
    }

    @Transactional
    public Game update(Long id, LocalDateTime startDate, LocalDateTime endDate, String region) {
        Game game = gameRepository.findOne(id);
        return gameRepository.update(game, startDate, endDate, region);
    }

    @Transactional
    public void remove(Long id) {
        Game game = gameRepository.findOne(id);
        gameRepository.remove(game);
    }

    public Game findOne(Long id) {
        return gameRepository.findOne(id);
    }

    public List<Game> findAll(int pages) {
        return gameRepository.findAll(pages);
    }

    public List<Game> findSearch(LocalDateTime dateTime, String region, int pages) {
        return gameRepository.findSearch(dateTime, region, pages);
    }

    public List<Game> findRegion(String region, int pages) {
        return gameRepository.findRegion(region, pages);
    }

    public List<Game> findDate(LocalDateTime date, int pages) {
        return gameRepository.findDate(date, pages);
    }
}
