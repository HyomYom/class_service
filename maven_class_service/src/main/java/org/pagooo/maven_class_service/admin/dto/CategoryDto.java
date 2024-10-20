package org.pagooo.maven_class_service.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pagooo.maven_class_service.admin.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    Long id;
    String categoryName;
    int sortValue;
    boolean usingYn;

    public static List<CategoryDto> of (List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        if(categories != null && !categories.isEmpty()) {
            for (Category category : categories) {
                categoryDtos.add(of(category));
            }
            return categoryDtos;
        }
        return categoryDtos;
    }

    public static CategoryDto of (Category category) {
         return CategoryDto.builder()
                 .id(category.getId())
                 .categoryName(category.getCategoryName())
                 .sortValue(category.getSortValue())
                 .usingYn(category.isUsingYn())
                 .build();

    }
}
