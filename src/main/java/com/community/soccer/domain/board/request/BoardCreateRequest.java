package com.community.soccer.domain.board.request;

import com.community.soccer.domain.member.Member;

public record BoardCreateRequest(String title, String content, Member member) { }
