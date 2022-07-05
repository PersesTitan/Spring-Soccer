package com.community.soccer.repository;

import com.community.soccer.domain.Member;
import com.community.soccer.domain.MemberEditDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;

@Controller
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public void remove(Member member) {
        em.remove(member);
    }

    //정보 업데이트
    public void update(Member member, MemberEditDao dto) {
        member.setLoginId(dto.loginId());
        member.setNickName(dto.nickName());
    }
}
