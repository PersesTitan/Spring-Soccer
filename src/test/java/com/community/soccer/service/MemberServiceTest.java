package com.community.soccer.service;

import com.community.soccer.domain.member.Member;
import com.community.soccer.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        Member member = Member.createMember("TestLoginId", "TestNickName");

        //when
        Long id = memberService.save(member);

        //then
        assertEquals(member, memberRepository.findOne(id));
    }
}