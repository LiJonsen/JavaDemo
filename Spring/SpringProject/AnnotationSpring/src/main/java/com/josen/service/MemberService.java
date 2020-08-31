package com.josen.service;

import com.josen.beans.Member;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MemberService
 * @Description
 * @Author Josen
 * @Create 2020/8/26 20:39
 */
public interface MemberService {
    List<Member> getMemberList() throws SQLException;
}
