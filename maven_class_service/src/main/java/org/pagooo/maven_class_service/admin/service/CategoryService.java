package org.pagooo.maven_class_service.admin.service;

import org.pagooo.maven_class_service.admin.dto.CategoryDto;
import org.pagooo.maven_class_service.admin.model.CategoryInput;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategories();

    /**
     * 카테고리 신규
     * @param categoryName
     * @return
     */
    boolean add(String categoryName);

    /**
     * 카테고리 수정
     * @param parameter
     * @return
     */
    boolean update(CategoryInput parameter);

    /**
     * 카테고리 삭제
     * @param id
     * @return
     */
    boolean delete(long id);


}
