package com.josen.service.impl;

import com.josen.beans.Member;
import com.josen.repository.MemberDao;
import com.josen.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MemberServiceImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/8/26 21:03
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public List<Member> getMemberList() throws SQLException {
        return memberDao.queryList();
    }
}
