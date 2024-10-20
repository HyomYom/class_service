package org.pagooo.maven_class_service.course.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.pagooo.maven_class_service.admin.model.MemberParam;
import org.pagooo.maven_class_service.admin.service.CategoryService;
import org.pagooo.maven_class_service.course.dto.CourseDto;
import org.pagooo.maven_class_service.course.model.CourseInput;
import org.pagooo.maven_class_service.course.model.CourseParam;
import org.pagooo.maven_class_service.course.service.CourseService;
import org.pagooo.maven_class_service.member.dto.MemberDto;
import org.pagooo.maven_class_service.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCourseController extends BaseController{

    private final CourseService courseService;
    private final CategoryService categoryService;


    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam parameter) {


        List<CourseDto> courseList = courseService.list(parameter);
        model.addAttribute("list", courseList);

        String queryString = parameter.getQueryString();
        long pageSize = 10;
        long totalCount = 0 ;
        if(!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);


        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/course/list";

    }

    @GetMapping(value={"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(Model model, HttpServletRequest request, CourseInput parameter) {


        //카테고리 정보를 내려줘야 함
        model.addAttribute("category",categoryService.getCategories());


        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if(editMode){
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);

            if(existCourse == null){
                // error 처리
                model.addAttribute("message","강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existCourse;
        }

        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);

        return "admin/course/add";
    }

    @PostMapping(value={"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model, CourseInput parameter, HttpServletRequest request) {
        boolean editMode = request.getRequestURI().contains("/edit.do");

        if(editMode){
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);

            if(existCourse == null){
                // error 처리
                model.addAttribute("message","강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = courseService.set(parameter);
        } else {
            boolean result = courseService.add(parameter);

        }


        return "redirect:/admin/course/list.do";
    }

}