package com.community.soccer.domain.member;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Setter private String loginId;
    @Setter private String nickName;
    private LocalDateTime createDate;

    private Member(String loginId, String nickName) {
        this.loginId = loginId;
        this.nickName = nickName;
        createDate = LocalDateTime.now();
    }

    //생성 로직
    public static Member createMember(String loginId, String nickName) {
        return new Member(loginId, nickName);
    }
}
