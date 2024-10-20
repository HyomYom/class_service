package org.pagooo.maven_class_service.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pagooo.maven_class_service.admin.model.MemberParam;
import org.pagooo.maven_class_service.member.dto.MemberDto;

import java.util.List;


@Mapper
public interface MemberMapper {
    long selectListCount(MemberParam memberParam);
    List<MemberDto> selectList(MemberParam parameter);
}
