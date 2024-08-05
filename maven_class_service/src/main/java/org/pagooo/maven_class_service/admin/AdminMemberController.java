package org.pagooo.maven_class_service.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMemberController {

    @GetMapping("/admin/list.do")
    public String list(){
        return "admin/member/list";
    }

}
