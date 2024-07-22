package org.pagooo.maven_class_service.member.repository;

import org.pagooo.maven_class_service.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {


    Optional<Member> findByEmailAuthKey(String emailAUthKey);
    Optional<Member> findByUserIdAndUserName(String userId, String userName);
    Optional<Member> findByResetPasswordKey(String resetPasswordKey);
}
