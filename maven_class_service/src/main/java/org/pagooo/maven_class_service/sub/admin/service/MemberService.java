package org.pagooo.maven_class_service.sub.admin.service;


import org.pagooo.maven_class_service.member.model.MemberInput;
import org.pagooo.maven_class_service.member.model.ResetPasswordInput;
import org.pagooo.maven_class_service.sub.admin.dto.MemberDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     * @param uuid
     * @return
     */
    boolean emailAuth(String uuid);

    /**
     * 입력받은 uuid에 대해서 password로 초기화 함
     */
    boolean sendResetPassword(ResetPasswordInput parameter );

    /**
     * 입력받은 uuid에 대해서 password로 초기화 함
     * @param  id(uuid)
     * @param  password
     * @return
     */
    boolean resetPassword(String id, String password);

    /**
     * uuid값이 유효한지 학인
     * @param uuid
     * @return
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 리턴(관려ㅣ자에서만 사용 가능)
     */
    List<MemberDto> list();
}