package org.pagooo.maven_class_service;


// mainPage 클래스를 만든 목적!!
// 주소와(놀리적인주소인터넷주소) 물리적인파일(프로그램이을 해야함) 매핑

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {

        return "index";
    }
}
