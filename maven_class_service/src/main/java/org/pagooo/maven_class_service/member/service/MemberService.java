package org.pagooo.maven_class_service.member.service;


import org.pagooo.maven_class_service.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     * @param uuid
     * @return
     */
    boolean emailAuth(String uuid);


}
