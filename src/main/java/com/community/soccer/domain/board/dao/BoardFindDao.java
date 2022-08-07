package com.community.soccer.domain.board.dao;

import com.community.soccer.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BoardFindDao(Long id, String title, String content, Member member,
                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime localDateTime) { }
