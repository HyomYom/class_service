package org.pagooo.maven_class_service.admin.model;

import lombok.Data;

@Data
public class MemberInput {
    String userId;
    String userStatus;
    String password;
}
