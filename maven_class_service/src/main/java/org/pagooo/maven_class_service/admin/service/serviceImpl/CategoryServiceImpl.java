package org.pagooo.maven_class_service.admin.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.admin.CategoryRepository;
import org.pagooo.maven_class_service.admin.dto.CategoryDto;
import org.pagooo.maven_class_service.admin.entity.Category;
import org.pagooo.maven_class_service.admin.model.CategoryInput;
import org.pagooo.maven_class_service.admin.service.CategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private Sort getSortBySortValueDesc(){
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }


    @Override
    public List<CategoryDto> getCategories() {
//        return categoryRepository.findAllOrderBySortValueDesc().map(CategoryDto::of).orElse(null);

        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
        return  CategoryDto.of(categories);
    }

    @Override
    public boolean add(String categoryName) {

        //카테고리명이 중복인거 체크


        Category category = Category.builder()
                        .categoryName(categoryName)
                        .usingYn(true)
                        .sortValue(0)
                        .build();

        categoryRepository.save(category);

        return true ;
    }

    @Override
    public boolean update(CategoryInput parameter) {
        Optional<Category> categoryOptional = categoryRepository.findById(parameter.getId());
        if(categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCategoryName(parameter.getCategoryName());
            category.setUsingYn(parameter.isUsingYn());
            category.setSortValue(parameter.getSortValue());
            categoryRepository.save(category);
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
