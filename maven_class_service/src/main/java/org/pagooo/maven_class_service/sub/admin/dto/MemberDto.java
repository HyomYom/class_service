package org.pagooo.maven_class_service.sub.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MemberDto {
     String userId;


     String userName;

     String phone;

     String password;

     LocalDateTime regDt;

     boolean emailAuthYn;
     LocalDateTime emailAuthDt;
     String emailAuthKey;

     String resetPasswordKey;
     LocalDateTime resetPasswordLimitDt;

     boolean adminYn;
}
