package org.pagooo.maven_class_service.admin.model;


import lombok.Data;

@Data
public class CategoryInput {

    private long id;
    private long categoryId;
    private String name;
    private String CategoryName;
    private int sortValue;
    private boolean usingYn;
}
