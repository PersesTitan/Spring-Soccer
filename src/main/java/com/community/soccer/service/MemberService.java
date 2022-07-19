package com.community.soccer.service;

import com.community.soccer.domain.member.Member;
import com.community.soccer.domain.member.dao.MemberEditDao;
import com.community.soccer.domain.member.request.EditMemberRequest;
import com.community.soccer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Member update(Long id, EditMemberRequest request) {
        Member member = memberRepository.findOne(id);
        String nickName = request.nickName();

        member.setNickName(nickName);
        return memberRepository.update(member, nickName);
    }

    @Transactional
    public void remove(Long id) {
        Member member = memberRepository.findOne(id);
        memberRepository.remove(member);
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findSearch(String keyWord) {
        if (keyWord == null || keyWord.isBlank()) return memberRepository.findAll();
        else return memberRepository.findSearch(keyWord);
    }
}
