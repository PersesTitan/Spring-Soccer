package com.community.soccer.domain.player.request;

import com.community.soccer.domain.item.Position;

public record PlayerCreateRequest(Position position, String phone) {
}
