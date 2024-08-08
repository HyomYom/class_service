package org.pagooo.maven_class_service.sub.admin.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.pagooo.maven_class_service.member.entity.Member;
import org.pagooo.maven_class_service.sub.admin.dto.MemberDto;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDto> selectList(MemberDto parameter);

}
