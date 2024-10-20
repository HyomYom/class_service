package org.pagooo.maven_class_service.course.model;


import lombok.Data;

@Data
public class CourseInput {
    long id;
    long categoryId;
    String subject;
}
