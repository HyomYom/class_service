package org.pagooo.maven_class_service.admin;

import org.pagooo.maven_class_service.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
