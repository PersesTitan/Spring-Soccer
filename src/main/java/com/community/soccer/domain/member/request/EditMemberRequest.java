package com.community.soccer.domain.member.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record EditMemberRequest(String id, String password, String loginId, String nickName,
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                LocalDateTime createDate) { }
