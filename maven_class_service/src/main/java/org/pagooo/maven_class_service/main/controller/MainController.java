package org.pagooo.maven_class_service.main.controller;


// mainPage 클래스를 만든 목적!!
// 주소와(놀리적인주소인터넷주소) 물리적인파일(프로그램이을 해야함) 매핑

import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.components.MailComponents;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {

        String email = "hyomyang@gmail.com";
        String subject = "안녕하세요 안녕하세요";
        String text = "<p>안녕하세요.</p><br><p>반갑습니다.</p>";

        boolean b = mailComponents.sendMail(email, subject, text);
        System.out.println(b);

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied(){
        return "error/denied";
    }
}
