package org.pagooo.maven_class_service.course.service;

import org.pagooo.maven_class_service.course.dto.CourseDto;
import org.pagooo.maven_class_service.course.model.CourseInput;
import org.pagooo.maven_class_service.course.model.CourseParam;

import java.util.List;

public interface CourseService {
    /**
     * 강좌 등록
     */
    boolean add(CourseInput courseInput);

    /**
     * 강좌 목록
     */
    List<CourseDto> list(CourseParam parameter);

    /**
     * 강좌 상세정보
     */

    CourseDto getById(long id);

    /**
     * 강좌 정보수정
     */

    boolean set(CourseInput parameter);
}
