package com.community.soccer.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

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

    //equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;
        return id != null && Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
