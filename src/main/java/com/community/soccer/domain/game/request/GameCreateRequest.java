package com.community.soccer.domain.game.request;

import com.community.soccer.domain.item.Position;

import java.time.LocalDateTime;
import java.util.Map;

public record GameCreateRequest(String title, String place, String region,
                                LocalDateTime startDate, LocalDateTime endDate,
                                Map<Position, Integer> requirePlayer, String description) { }
