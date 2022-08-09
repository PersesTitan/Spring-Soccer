package com.community.soccer.controller;

import com.community.soccer.domain.member.Member;
import com.community.soccer.domain.member.dao.MemberCreateDao;
import com.community.soccer.domain.member.dao.MemberEditDao;
import com.community.soccer.domain.member.dao.MemberFindDao;
import com.community.soccer.domain.member.request.CreateMemberRequest;
import com.community.soccer.domain.member.request.EditMemberRequest;
import com.community.soccer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("")
    public MemberCreateDao createMember(@RequestBody @Valid CreateMemberRequest request) {
        String loginId = request.loginId();
        String nickName = request.nickName();
        String password = request.password();
        Member member = Member.createMember(loginId, nickName, password);
        memberService.save(member);
        return new MemberCreateDao(member.getId());
    }

    @GetMapping("/{id}")
    public MemberFindDao findOne(@PathVariable("id") Long id) {
        Member member = memberService.findOne(id);
        String nickName = member.getNickName();
        LocalDateTime createDate = member.getCreateDate();

        return new MemberFindDao(id, nickName, createDate);
    }

    @PatchMapping("/{id}")
    public MemberEditDao findEdit(@PathVariable("id") Long id,
                                  @RequestBody @Valid EditMemberRequest request) {
        String nickName = request.nickName();
        String password = request.password();
        String loginId = request.loginId();
        LocalDateTime createDate = request.createDate();

        return new MemberEditDao(id, password, loginId, nickName, createDate);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        memberService.remove(id);
    }

    @GetMapping("")
    public List<MemberFindDao> memberSearch(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        return memberService.findSearch(keyword)
                .stream()
                .map(o -> new MemberFindDao(
                        o.getId(),
                        o.getNickName(),
                        o.getCreateDate()))
                .collect(Collectors.toList());
    }
}
