package com.community.soccer.controller;

import com.community.soccer.domain.game.Game;
import com.community.soccer.domain.game.dao.GameCreateDao;
import com.community.soccer.domain.game.dao.GameEditDao;
import com.community.soccer.domain.game.dao.GameFindDao;
import com.community.soccer.domain.game.dao.GameSearchDao;
import com.community.soccer.domain.game.request.GameCreateRequest;
import com.community.soccer.domain.game.request.GameEditRequest;
import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.item.PositionDto;
import com.community.soccer.domain.player.Player;
import com.community.soccer.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameApiController {

    private final GameService gameService;

    @PostMapping("")
    public GameCreateDao save(@RequestBody @Valid GameCreateRequest gameCreateRequest) {
        String title = gameCreateRequest.title();
        String place = gameCreateRequest.place();
        String region = gameCreateRequest.region();
        LocalDateTime startDate = gameCreateRequest.startDate();
        LocalDateTime endDate = gameCreateRequest.endDate();
        String description = gameCreateRequest.description();

        Map<Position, Integer> requirePlayer = gameCreateRequest.requirePlayer();
        Integer masterFW = requirePlayer.get(Position.FW);
        Integer masterMF = requirePlayer.get(Position.MF);
        Integer masterDF = requirePlayer.get(Position.DF);
        Integer masterGK = requirePlayer.get(Position.GK);

        PositionDto positionDto = new PositionDto(masterFW, masterMF, masterDF, masterGK);
        Game game = Game.createGame(title, place, region, startDate, endDate, description, positionDto);
        Long id = gameService.save(game);

        return new GameCreateDao(id, title, place, region, startDate, endDate, requirePlayer, description);
    }

    @GetMapping("/{id}")
    public GameFindDao find(@PathVariable Long id) {
        Game game = gameService.findOne(id);

        String title = game.getTitle();
        String place = game.getPlace();
        String region = game.getRegion();
        LocalDateTime startDate = game.getStartDate();
        LocalDateTime endDate = game.getEndDate();
        String description = game.getDescription();
        List<Player> players = game.getPlayers();

        //날짜 확인
        overDateRemove(id, endDate);

        return new GameFindDao(id, title, place, region, startDate, endDate, setRequirePlayer(game), description, players);
    }

    @PatchMapping("/{id}")
    public GameEditDao edit(@PathVariable Long id,
                            @RequestBody @Valid GameEditRequest gameEditRequest) {
        Game game = gameService.findOne(id);
        LocalDateTime startDate = gameEditRequest.startDate();
        LocalDateTime endDate = gameEditRequest.endDate();
        String region = gameEditRequest.region();

        gameService.update(id, startDate, endDate, region);

        String title = game.getTitle();
        String place = game.getPlace();
        String description = game.getDescription();
        List<Player> players = game.getPlayers();

        return new GameEditDao(id, title, place, region, startDate, endDate, setRequirePlayer(game), description, players);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gameService.remove(id);
    }

    @GetMapping("")
    public GameSearchDao search(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "region", defaultValue = "") String region,
            @RequestParam(value = "localDateTime", defaultValue = "") String localDateTime) {
        List<Game> search = getGameData(localDateTime, region, page);

        //날짜 확인
        for (Game game : search) overDateRemove(game.getId(), game.getEndDate());
        return new GameSearchDao(search);
    }

    // 조건에 맞는 값 반환
    private List<Game> getGameData(String localDateTime, String region, int page) {
        if (!localDateTime.isBlank()) {
            LocalDateTime date = LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return !region.isBlank() ?
                    gameService.findSearch(date, region, page) :
                    gameService.findDate(date, page);
        } else return !region.isBlank() ?
                    gameService.findRegion(region, page) :
                    gameService.findAll(page);
    }

    private Map<Position, Integer> setRequirePlayer(Game game) {
        Map<Position, Integer> requirePlayer = new HashMap<>();
        requirePlayer.put(Position.FW, game.getMasterFW());
        requirePlayer.put(Position.MF, game.getMasterMF());
        requirePlayer.put(Position.DF, game.getMasterDF());
        requirePlayer.put(Position.GK, game.getMasterGK());
        return requirePlayer;
    }

    //날짜가 지난 게임 삭제
    private void overDateRemove(Long id, LocalDateTime endDate) {
        if (endDate.isBefore(LocalDateTime.now())) {
            gameService.remove(id);
        }
    }
}
