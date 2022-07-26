package com.community.soccer.domain.game.dao;

import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record GameFindDao(Long id, String title, String place, String region,
                          @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                          LocalDateTime startDate,
                          @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                          LocalDateTime endDate,
                          Map<Position, Integer> requirePlayer,
                          String description, List<Player> players) { }
