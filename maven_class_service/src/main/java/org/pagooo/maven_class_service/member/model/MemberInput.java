package org.pagooo.maven_class_service.member.model;


import lombok.Data;

@Data
public class MemberInput {
    private String userId;
    private String userName;
    private String phone;
    private String password;
}
