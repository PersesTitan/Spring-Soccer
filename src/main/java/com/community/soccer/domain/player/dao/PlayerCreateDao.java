package com.community.soccer.domain.player.dao;

import com.community.soccer.domain.item.Position;

public record PlayerCreateDao(Long id, Position position, String phone) {
}
