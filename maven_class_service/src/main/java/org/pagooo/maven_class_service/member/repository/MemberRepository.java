package org.pagooo.maven_class_service.member.repository;

import org.pagooo.maven_class_service.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
