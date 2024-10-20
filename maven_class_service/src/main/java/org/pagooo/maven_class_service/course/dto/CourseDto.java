package org.pagooo.maven_class_service.course.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pagooo.maven_class_service.course.entity.Course;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

     Long id;

    String imagePath;
    String keyword;
    String subject;


    String summary;


    String contents;
    String price;
    String salesPrice;

    LocalDateTime saleEndDt;

    LocalDateTime regDt; //등록일(추가날짜)
    LocalDateTime udtDt; //수정일(수정날짜)



    long totalCount;
    long seq;

    public static CourseDto of(Course course) {
    return CourseDto.builder()
            .id(course.getId())
            .imagePath(course.getImagePath())
            .keyword(course.getKeyword())
            .subject(course.getSubject())
            .summary(course.getSummary())
            .contents(course.getContents())
            .price(course.getPrice())
            .salesPrice(course.getSalesPrice())
            .saleEndDt(course.getSaleEndDt())
            .regDt(course.getRegDt())
            .udtDt(course.getUdtDt())
            .build();
    }
}
