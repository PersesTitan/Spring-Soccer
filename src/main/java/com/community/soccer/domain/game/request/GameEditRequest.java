package com.community.soccer.domain.game.request;

import java.time.LocalDateTime;

public record GameEditRequest(LocalDateTime startDate, LocalDateTime endDate, String region) { }
