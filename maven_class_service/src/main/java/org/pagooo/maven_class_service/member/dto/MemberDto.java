package org.pagooo.maven_class_service.member.dto;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.pagooo.maven_class_service.member.entity.Member;
import org.pagooo.maven_class_service.util.BooleanToBitConverter;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;
    LocalDateTime emailAuthDt;
    LocalDateTime resetPasswordLimitDt;
    String emailAuthKey;
    String resetPasswordKey;
    long totalCount;
    long seq;
    @Convert(converter = BooleanToBitConverter.class)
    boolean emailAuthYn;
    @Convert(converter = BooleanToBitConverter.class)
    boolean adminYn;
    String userStatus;

    public static MemberDto of(Member member){

        return MemberDto.builder()
                 .userId(member.getUserId())
                 .userName(member.getUserName())
                 .phone(member.getPhone())
                 .regDt(member.getRegDt())
                 .emailAuthYn(member.isEmailAuthYn())
                 .emailAuthDt(member.getEmailAuthDt())
                 .emailAuthKey(member.getEmailAuthKey())
                 .resetPasswordKey(member.getResetPasswordKey())
                 .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                 .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                 .build();

    }

}