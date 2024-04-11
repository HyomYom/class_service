package org.pagooo.maven_class_service.member.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.model.MemberInput;
import org.pagooo.maven_class_service.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;



//    @RequestMapping(value = "/member/register", method = RequestMethod.GET)
    @GetMapping("/member/register")
    public String register(){
        System.out.println("request get");
        return "member/register";
    }

//    @RequestMapping(value = "/member/register",method = RequestMethod.POST)
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, MemberInput parameter){

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);


        return "member/register_complete";
    }
}
