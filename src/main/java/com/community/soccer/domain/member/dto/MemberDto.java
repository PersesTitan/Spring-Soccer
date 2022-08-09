package com.community.soccer.domain.member.dto;

import java.time.LocalDateTime;

public record MemberDto(Long id, String nickName, LocalDateTime createDate) {
}
