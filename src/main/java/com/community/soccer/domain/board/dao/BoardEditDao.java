package com.community.soccer.domain.board.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BoardEditDao<T>(Long id, String title, String content, T member,
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                              LocalDateTime localDateTime) { }
