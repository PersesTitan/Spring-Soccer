package com.community.soccer.controller;

import com.community.soccer.domain.member.Member;
import com.community.soccer.domain.member.dao.MemberCreateDao;
import com.community.soccer.domain.member.request.CreateMemberRequest;
import com.community.soccer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public MemberCreateDao createMemberDao(@RequestBody @Valid CreateMemberRequest createMemberRequest) {
        String loginId = createMemberRequest.loginId();
        String nickName = createMemberRequest.nickName();
        Member member = Member.createMember(loginId, nickName);

        Long id = memberService.save(member);
        return new MemberCreateDao(id);
    }



}
