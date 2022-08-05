package com.community.soccer.domain.member.request;

public record CreateMemberRequest(String loginId, String nickName, String password) {
}
