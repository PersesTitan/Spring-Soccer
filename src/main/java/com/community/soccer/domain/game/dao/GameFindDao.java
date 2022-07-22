package com.community.soccer.domain.game.dao;

import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.item.PositionDto;
import com.community.soccer.domain.player.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record GameFindDao(Long id, String title, String place, String region,
                          LocalDateTime startDate, LocalDateTime endDate,
                          Map<Position, Integer> requirePlayer,
                          String description, List<Player> players) { }
