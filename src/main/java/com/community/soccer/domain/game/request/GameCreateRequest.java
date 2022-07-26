package com.community.soccer.domain.game.request;

import com.community.soccer.domain.item.Position;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Map;

public record GameCreateRequest(String title, String place, String region,
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                LocalDateTime startDate,
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                LocalDateTime endDate,
                                Map<Position, Integer> requirePlayer, String description) { }
