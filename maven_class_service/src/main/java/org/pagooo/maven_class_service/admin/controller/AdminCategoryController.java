package org.pagooo.maven_class_service.admin.controller;


import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.admin.dto.CategoryDto;
import org.pagooo.maven_class_service.admin.model.CategoryInput;
import org.pagooo.maven_class_service.admin.model.MemberParam;
import org.pagooo.maven_class_service.admin.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;


    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam memberParam){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        return "admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(Model  model, CategoryInput parameter){
        boolean add = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do")
    public String del(Model  model, CategoryInput parameter){
        boolean result = categoryService.delete(parameter.getId());
        return "redirect:/admin/category/list.do";
    }


    @PostMapping("/admin/category/update.do")
    public String update(Model  model, CategoryInput parameter){
        boolean result = categoryService.update(parameter);
        return "redirect:/admin/category/list.do";
    }

}
