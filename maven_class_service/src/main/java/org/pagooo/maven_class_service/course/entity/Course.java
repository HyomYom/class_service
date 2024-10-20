package org.pagooo.maven_class_service.course.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    long categoryId;

    String imagePath;
    String keyword;
    String subject;

    @Column(length = 1000)
    String summary;

    @Lob
    String contents;
    String price;
    String salesPrice;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime saleEndDt;

    LocalDateTime regDt; //등록일(추가날짜)
    LocalDateTime udtDt; //수정일(수정날짜)





}
