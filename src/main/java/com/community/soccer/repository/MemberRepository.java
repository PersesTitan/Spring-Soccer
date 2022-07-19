package com.community.soccer.repository;

import com.community.soccer.domain.member.Member;
import com.community.soccer.domain.member.dao.MemberEditDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
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

    //아이디 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //모든 맴버 리스트
    public List<Member> findAll() {
        return em.createQuery("SELECT M FROM Member AS M", Member.class)
                .getResultList();
    }

    //닉네임 검색 로직
    public List<Member> findSearch(String keyWord) {
        return em.createQuery("SELECT M FROM Member AS M WHERE M.nickName LIKE :keyWord", Member.class)
                .setParameter("keyWord", "%"+keyWord+"%")
                .getResultList();
    }
}
