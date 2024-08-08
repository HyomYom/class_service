package org.pagooo.maven_class_service.sub.admin.admin;


import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.entity.Member;
import org.pagooo.maven_class_service.sub.admin.dto.MemberDto;
import org.pagooo.maven_class_service.sub.admin.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model) {

        List<MemberDto> memberList = memberService.list();
        model.addAttribute("list", memberList);
        return "admin/member/list";
    }

}
