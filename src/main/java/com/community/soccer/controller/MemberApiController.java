package com.community.soccer.controller;

import com.community.soccer.domain.member.Member;
import com.community.soccer.domain.member.dao.MemberCreateDao;
import com.community.soccer.domain.member.dao.MemberEditDao;
import com.community.soccer.domain.member.dao.MemberFindDao;
import com.community.soccer.domain.member.dao.MemberSearchDao;
import com.community.soccer.domain.member.request.CreateMemberRequest;
import com.community.soccer.domain.member.request.EditMemberRequest;
import com.community.soccer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/{id}")
    public MemberFindDao findMemberDao(@PathVariable Long id) {
        Member member = memberService.findOne(id);
        String nickName = member.getNickName();
        LocalDateTime createDate = member.getCreateDate();

        return new MemberFindDao(id, nickName, createDate);
    }

    @PatchMapping("/{id}")
    public MemberEditDao editMemberDao(@PathVariable Long id,
                                       @RequestBody @Valid EditMemberRequest editMemberRequest) {
        Member member = memberService.update(id, editMemberRequest);
        String nickName = member.getNickName();
        LocalDateTime createDate = member.getCreateDate();
        String loginId = member.getLoginId();

        return new MemberEditDao(id, loginId, nickName, createDate);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberDao(@PathVariable Long id) {
        memberService.remove(id);
    }

    @GetMapping
    public MemberSearchDao searchDao(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        List<Member> members = memberService.findSearch(keyword);
        return new MemberSearchDao(members);
    }

}
