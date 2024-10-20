package org.pagooo.maven_class_service.admin.controller;


import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.admin.model.MemberParam;
import org.pagooo.maven_class_service.admin.model.MemberInput;
import org.pagooo.maven_class_service.course.controller.BaseController;
import org.pagooo.maven_class_service.member.dto.MemberDto;
import org.pagooo.maven_class_service.member.service.MemberService;
import org.pagooo.maven_class_service.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController extends BaseController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam memberParam) {

        List<MemberDto> memberList = memberService.list(memberParam);
        model.addAttribute("list", memberList);

        String queryString = memberParam.getQueryString();
        long pageSize = 10;
        long totalCount = 0 ;
        if(memberList != null && !memberList.isEmpty()) {
            totalCount = memberList.get(0).getTotalCount();
        }
        String pagerHtml = getPagerHtml(totalCount, memberParam.getPageSize(), memberParam.getPageIndex(), queryString);


        model.addAttribute("list", memberList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);


        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam memberParam) {



        MemberDto memberDto = memberService.detail(memberParam.getUserId());

        model.addAttribute("member", memberDto);

        return "admin/member/detail";
    }

    @PostMapping("/admin/member/status.do")
    public String status(Model model, MemberInput memberInput ){
        boolean result = memberService.updateStatus(memberInput.getUserId(), memberInput.getUserStatus());
        if(!result){
            return "redirect:/admin/member/list.do";
        }
        return "redirect:/admin/member/detail.do?userId="+memberInput.getUserId();
    }

    @PostMapping("/admin/member/password.do")
    public String password(Model model, MemberInput memberInput ){
        boolean result = memberService.updatePassword(memberInput.getUserId(), memberInput.getPassword());
        if(!result){
            return "redirect:/admin/member/list.do";
        }
        return "redirect:/admin/member/detail.do?userId="+memberInput.getUserId();
    }


}