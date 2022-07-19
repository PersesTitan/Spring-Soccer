package com.community.soccer.domain.member.dao;

import java.time.LocalDateTime;

public record MemberFindDao(Long id, String nickName, LocalDateTime createDate) {
}