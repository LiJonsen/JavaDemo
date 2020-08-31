package com.josen.repository;

import com.josen.beans.Member;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MemberDao
 * @Description
 * @Author Josen
 * @Create 2020/8/26 20:38
 */
public interface MemberDao {
    List<Member> queryList() throws SQLException;
}
