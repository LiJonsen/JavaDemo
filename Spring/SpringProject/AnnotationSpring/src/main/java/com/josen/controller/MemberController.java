package com.josen.controller;

import com.josen.beans.Member;
import com.josen.service.MemberService;
import com.josen.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MemberController
 * @Description Details
 * @Author Josen
 * @Create 2020/8/26 20:57
 */
@Controller("memberController")
public class MemberController {
    @Autowired
    private MemberService memberService;
    public void echoMemberList(){
        try {
            List<Member> memberList = memberService.getMemberList();
            for (Member member : memberList) {
                System.out.println(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
