package com.josen.mapper;

import com.josen.pojo.Member;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName MemberMapper
 * @Description t_member会员表操作
 * @Author Josen
 * @Create 2020/9/11 19:44
 */
public interface MemberMapper {
    /**
     * 根据手机号查询
     * @return
     */
    Member queryMemberByPhone(@Param("phone") String phone);
    /**
     * 根据memberId查询
     * @return
     */
    Member queryMemberById(@Param("id") String id);

    /**
     * 新增一条记录
     */
    void insertMember(Member member);
}
