package com.community.soccer.domain.game.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record GameEditRequest(@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                              LocalDateTime startDate,
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                              LocalDateTime endDate, String region) { }
