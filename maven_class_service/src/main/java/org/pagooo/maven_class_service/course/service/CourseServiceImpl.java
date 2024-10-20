package org.pagooo.maven_class_service.course.service;

import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.course.dto.CourseDto;
import org.pagooo.maven_class_service.course.entity.Course;
import org.pagooo.maven_class_service.course.mapper.CourseMapper;
import org.pagooo.maven_class_service.course.model.CourseInput;
import org.pagooo.maven_class_service.course.model.CourseParam;
import org.pagooo.maven_class_service.course.repository.CourseRepository;
import org.pagooo.maven_class_service.member.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public boolean add(CourseInput parameter) {

        Course course = Course.builder().
                subject(parameter.getSubject())
                .categoryId(parameter.getCategoryId())
                .regDt(LocalDateTime.now())
                        .build();

        courseRepository.save(course);
        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam parameter) {
        long totalCount = courseMapper.selectListCount(parameter);
        List<CourseDto> courseDtos = courseMapper.selectList(parameter);

        if(!CollectionUtils.isEmpty(courseDtos)){
            int i = 0;
            for(CourseDto courseDto : courseDtos){
                courseDto.setTotalCount(totalCount);
                courseDto.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return courseDtos;
    }

    @Override
    public CourseDto getById(long id) {

        return courseRepository.findById((int) id).map(CourseDto::of).orElse(null);
    }

    @Override
    public boolean set(CourseInput parameter) {
        Optional<Course> courseOptional = courseRepository.findById((int) parameter.getId());
        if(!courseOptional.isPresent()){
            //수정할 데이터 없음
            return false;
        }

        Course course = courseOptional.get();

        course.setSubject(parameter.getSubject());
        course.setUdtDt(LocalDateTime.now());
        course.setCategoryId(parameter.getCategoryId());
        courseRepository.save(course);

        return true;
    }
}
