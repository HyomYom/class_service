package org.pagooo.maven_class_service.member.service;


import org.pagooo.maven_class_service.member.model.MemberInput;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    boolean register(MemberInput parameter);
}
