package org.pagooo.maven_class_service.member.service;

import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.entity.Member;
import org.pagooo.maven_class_service.member.model.MemberInput;
import org.pagooo.maven_class_service.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if(optionalMember.isPresent()){
            //현재 userId에 해당하는 데이터 존재

            return false;
        }


        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());
        memberRepository.save(member);
        return true;
    }
}
