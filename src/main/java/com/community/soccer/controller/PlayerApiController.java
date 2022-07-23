package com.community.soccer.controller;

import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.player.Player;
import com.community.soccer.domain.player.dao.PlayerCreateDao;
import com.community.soccer.domain.player.dao.PlayerFindDao;
import com.community.soccer.domain.player.request.PlayerCreateRequest;
import com.community.soccer.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class PlayerApiController {

    private final PlayerService playerService;

    @PostMapping("{game_id}/players")
    public PlayerCreateDao create(@PathVariable(name = "game_id") Long gameId,
                                  @RequestBody @Valid PlayerCreateRequest playerCreateRequest) {
        String phone = playerCreateRequest.phone();
        Position position = playerCreateRequest.position();
        Player player = playerService.save(gameId, position, phone);
        return new PlayerCreateDao(player.getId(), position, phone);
    }

    @GetMapping("{game_id}/players")
    public PlayerFindDao find(@PathVariable(name = "game_id") Long gameId) {
        List<Player> players = playerService.findAll(gameId);
        return new PlayerFindDao(players);
    }

    @DeleteMapping("{game_id}/players/{player_id}")
    public void remove(@PathVariable(name = "game_id") Long gameId,
                       @PathVariable(name = "player_id") Long playerId) {
        playerService.remove(gameId, playerId);
    }
}
