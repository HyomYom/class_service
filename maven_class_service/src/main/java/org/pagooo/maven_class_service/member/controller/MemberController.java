package org.pagooo.maven_class_service.member.controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.model.MemberInput;
import org.pagooo.maven_class_service.member.model.ResetPasswordInput;
import org.pagooo.maven_class_service.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;




    @RequestMapping("/member/login")
    public String login(){
        return "member/login";
    }

//    @RequestMapping(value = "/member/register", method = RequestMethod.GET)
    @GetMapping("/member/register")
    public String register(){
        System.out.println("request get");
        return "member/register";
    }

//    @RequestMapping(value = "/member/register",method = RequestMethod.POST)
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, MemberInput parameter){

        boolean result = false;
        try {
            result = memberService.register(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("result", result);


        return "member/register_complete";
    }


    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result",result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(){
        return "member/info";
    }

    @GetMapping("/member/find/password")
    public String findPassword(){

        return"member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(Model model, ResetPasswordInput passwordInput){
        boolean result = memberService.sendResetPassword(passwordInput);

        model.addAttribute("result",result);
        return "member/find_password_result"; // 이동 및 주소변경 동시에
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");
        model.addAttribute("uuid",uuid);

        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result",result);

        return "member/reset_password";

    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput passwordInput){
        boolean result = false;
        try {
             result = memberService.resetPassword(passwordInput.getId(), passwordInput.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("result",result);

        return "member/reset_password_result";
    }

}
