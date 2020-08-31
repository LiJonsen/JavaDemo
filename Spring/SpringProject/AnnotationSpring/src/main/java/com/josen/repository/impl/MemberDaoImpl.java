package com.josen.repository.impl;

import com.josen.beans.Member;
import com.josen.repository.MemberDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MemberDaoImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/8/26 20:39
 */
@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private QueryRunner runner;

    @Override
    public List<Member> queryList() throws SQLException {
        String sql = "select id,nick_name nickName,avatar_url avatarUrl,city from t_wx_member";
        return runner.query(sql,new BeanListHandler<>(Member.class));
    }
}
