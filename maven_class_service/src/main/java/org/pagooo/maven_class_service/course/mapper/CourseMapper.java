package org.pagooo.maven_class_service.course.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.pagooo.maven_class_service.course.dto.CourseDto;
import org.pagooo.maven_class_service.course.model.CourseParam;

import java.util.List;

@Mapper
public interface CourseMapper {
    long selectListCount(CourseParam parameter);
    List<CourseDto> selectList(CourseParam parameter);
}
