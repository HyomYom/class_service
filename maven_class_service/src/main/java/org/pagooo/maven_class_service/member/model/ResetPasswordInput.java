package org.pagooo.maven_class_service.member.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResetPasswordInput {
    private String userId;
    private String userName;
    private String password;
    private String id;
}
